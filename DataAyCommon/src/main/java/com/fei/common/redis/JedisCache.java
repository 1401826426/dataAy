package com.fei.common.redis;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;

public class JedisCache implements Cache{
	
	private String id ; 

//	private Jedis jedis ; 
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void putObject(Object key, Object value) {
		
		
	}

	@Override
	public Object getObject(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		// TODO Auto-generated method stub
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

}
