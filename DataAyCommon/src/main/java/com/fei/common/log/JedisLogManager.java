package com.fei.common.log;

import com.fei.common.rpc.framework.converter.Converter;
import com.fei.common.zookeeper.server.Server;

public class JedisLogManager {
	
	private static JedisLogManager instance = new JedisLogManager() ; 
	
	private JedisManager jedisManager ; 
	
	private Server selfServer ; 
	
	private Converter converter ; 
	
	public static JedisLogManager getInstance(){
		return instance ; 
	}
	
	public void registerJedisManager(JedisManager jedisManager){
		this.jedisManager = jedisManager ; 
	}
	
	public void registerSelfServer(Server server){
		this.selfServer = server ; 
	}

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public Server getSelfServer() {
		return selfServer;
	}

	public Converter getConverter() {
		if(converter == null){
			synchronized (this) {
				if(converter == null){
//					this.converter = new FackJsonConverter() ; 
				}
			}
		}
		return converter;
	}
	
	
	
}
