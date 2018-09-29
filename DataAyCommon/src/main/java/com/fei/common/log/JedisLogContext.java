package com.fei.common.log;

import com.fei.common.converter.Converter;
import com.fei.common.converter.fackson.FackJsonConverter;
import com.fei.common.zookeeper.server.Server;

import redis.clients.jedis.Jedis;

public class JedisLogContext {
	
	private static JedisLogContext instance = new JedisLogContext() ; 
	
	private JedisManager jedisManager ; 
	
	private Server selfServer ; 
	
	private Converter converter ; 
	
	public static JedisLogContext getInstance(){
		return instance ; 
	}
	
	public void registerJedisManager(JedisManager jedisManager){
		this.jedisManager = jedisManager ; 
	}
	
	public void registerSelfServer(Server server){
		this.selfServer = server ; 
	}

	public Jedis getJedis(){
		return jedisManager.getJedis() ; 
	}

	public Server getSelfServer() {
		return selfServer;
	}

	public Converter getConverter() {
		if(converter == null){
			synchronized (this) {
				if(converter == null){
					this.converter = new FackJsonConverter() ; 
				}
			}
		}
		return converter;
	}

	public void registerConverter(Converter converter) {
		this.converter = converter; 
	}
	
	
	
}
