package com.fei.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import com.fei.netty.springmvc.converter.Converter;
import com.fei.netty.springmvc.converter.ConverterException;

public class JedisIdCache implements Cache{
	
	private String id ; 
	
	private byte[] idBytes ; 
	
	private ReadWriteLock lock = new ReentrantReadWriteLock() ; 
	
	public JedisIdCache(String id){
		this.id = id ;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObject(Object key, Object value) {
		List<String> idKeys = CacheKeyUtil.resolveId(key) ; 
		if(idKeys == null){
			return ; 
		}
		try {
			byte[] idBytes = getIdBytes() ;
			Map<byte[],byte[]> map = new HashMap<byte[],byte[]>() ;
			List<Object> valueList = (List<Object>)value ;
			Converter converter = JedisCacheContext.getInstance().getConverter() ; 
			for(int i = 0;i < idKeys.size();i++){
				byte[] idKeyBytes = converter.writeValue(idKeys.get(i)) ; 
				byte[] valueBytes = converter.writeValue(valueList.get(i)) ;
				map.put(idKeyBytes, valueBytes) ; 
			}
			JedisCacheContext.getInstance().getJedisHashCommand().hmset(idBytes, map) ; 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private byte[] getIdBytes() throws ConverterException {
		if(idBytes == null){
			idBytes = JedisCacheContext.getInstance().getConverter().writeValue(id) ; 
		}
		return idBytes ;
	}

	@Override
	public Object getObject(Object key) {
	    List<String> idKeys = CacheKeyUtil.resolveId(key) ; 
	    if(idKeys == null){
	    	return  null; 
	    }
	    try {
			byte[] idBytes = getIdBytes() ;
			byte[][] bytes = new byte[idKeys.size()][] ; 
			for(int i = 0;i < idKeys.size();i++){
				byte[] idKeyBytes = JedisCacheContext.getInstance().getConverter().writeValue(idKeys.get(0)) ; 
				bytes[i] = idKeyBytes ; 
			}
			List<byte[]> values = JedisCacheContext.getInstance().getJedisHashCommand().hmget(idBytes,bytes) ;
			List<Object> results = new ArrayList<>(); 
			for(byte[] bs:values){
				if(bs == null){ //存在有id少的情况,则直接从原来的数据中查找
					return null ; 
				}
				Object obj = JedisCacheContext.getInstance().getConverter().readValue(bs, Object.class) ; 
				results.add(obj) ; 
			}   
			return results;  
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		return null;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public int getSize() {
		try {
			byte[] idBytes = getIdBytes() ;
			JedisCacheContext.getInstance().getJedisHashCommand().hlen(idBytes) ; 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return lock;
	}
	
}
