package com.fei.common.cache;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import com.fei.common.converter.ConverterException;

public class JedisCache implements Cache{
	
	private String id ;  
	
	private byte[] idBytes ; 
	
	private ReadWriteLock lock = new ReentrantReadWriteLock() ; 
	
	public JedisCache(String id){
		this.id = id ; 
		try {
			this.idBytes = id.getBytes("utf-8") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void putObject(Object key, Object value) { 
		try {
			byte[] keyBytes = JedisCacheContext.getInstance().getConverter().writeValue(key) ;
			byte[] valueBytes = JedisCacheContext.getInstance().getConverter().writeValue(value) ; 
			JedisCacheContext.getInstance().getJedisHashCommand().hset(idBytes, keyBytes, valueBytes) ; 
		} catch (ConverterException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getObject(Object key) {
		try{
			byte[] keyBytes = JedisCacheContext.getInstance().getConverter().writeValue(key) ;
			byte[] valueBytes = JedisCacheContext.getInstance().getJedisHashCommand().hget(idBytes, keyBytes) ; 
			return JedisCacheContext.getInstance().getConverter().readValue(valueBytes, null) ; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		try{
			byte[] keyBytes = JedisCacheContext.getInstance().getConverter().writeValue(key) ;
			byte[] valueBytes = JedisCacheContext.getInstance().getJedisHashCommand().hget(idBytes, keyBytes) ; 
			Object result = JedisCacheContext.getInstance().getConverter().readValue(valueBytes, null) ;
			JedisCacheContext.getInstance().getJedisHashCommand().hdel(idBytes, keyBytes) ; 
			return result ; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void clear() {
		JedisCacheContext.getInstance().getJedisHashCommand().del(idBytes); 
	}

	@Override
	public int getSize() {
		long len = JedisCacheContext.getInstance().getJedisHashCommand().hlen(idBytes); 
		return (int)len ; 
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return lock;
	}
	

}
