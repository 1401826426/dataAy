package com.fei.common.log;

import com.fei.common.converter.Converter;
import com.fei.common.converter.fackson.FackJsonConverter;
import com.fei.common.redis.IJedisPubSub;
import com.fei.common.zookeeper.server.Server;

public class JedisLogContext {
	
	private static JedisLogContext instance = new JedisLogContext() ; 
	
	private IJedisPubSub jedisPubSub ; 
	
	private Server selfServer ; 
	
	private Converter converter ; 
	
	public static JedisLogContext getInstance(){
		return instance ; 
	}
	
	public void registerJedisPubSub(IJedisPubSub jedisPubSub){
		this.jedisPubSub = jedisPubSub ;
	}
	
	public void registerSelfServer(Server server){
		this.selfServer = server ; 
	}

	public IJedisPubSub getJedisPubSub(){
		return jedisPubSub ; 
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
