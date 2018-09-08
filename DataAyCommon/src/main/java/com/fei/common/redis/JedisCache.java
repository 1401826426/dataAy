package com.fei.common.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;

import com.fei.common.converter.Converter;
import com.fei.common.converter.ConverterException;

import redis.clients.jedis.Jedis;

public class JedisCache implements Cache {

	private Jedis jedis;

	private Converter converter;

	public Jedis getJedis() {
		return jedis;
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public Converter getConverter() {
		return converter;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}

	@Override
	public String getId() {
		return "redis-cache";
	}

	@Override
	public void putObject(Object key, Object value) {
		// byte[] keyBytes = converter.writeValue(key) ;
		// byte[] valBytes = converter.writeValueWithType(value) ;
		if (key instanceof CacheKey) {
			// byte[] keyBytes = encode(key) ;
			byte[] keyBytes = encodeCacheKey((CacheKey) key);
			byte[] valBytes = encode(value);
			if (keyBytes != null && valBytes != null) {
				jedis.set(keyBytes, valBytes);
			}
		}
	}

	@Override
	public Object getObject(Object key) {
		if (key != null && key instanceof CacheKey) {
			byte[] keyBytes = encodeCacheKey((CacheKey) key);
			// byte[] keyBytes = encode(key) ;
			byte[] valBytes = jedis.get(keyBytes);
			if (valBytes == null || valBytes.length == 0) {
				return null;
			}
			return decode(valBytes);
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		try {
			byte[] keyBytes = converter.writeValue(key);
			jedis.del(keyBytes);
		} catch (ConverterException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void clear() {
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return null;
	}

	private byte[] encode(Object obj) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Object decode(byte[] bytes) {
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private byte[] encodeCacheKey(CacheKey key) {
		Class<?> clazz = CacheKey.class;
		Field field;
		try {
			field = clazz.getDeclaredField("updateList");
			field.setAccessible(true);
			List<Object> val = (List<Object>) field.get(key);
			return converter.writeValue(val);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
