package com.fei.common.cache;

import com.fei.common.converter.Converter;
import com.fei.common.converter.fackson.JavaConverter;
import com.fei.common.redis.IJedisHashCommand;

public class JedisCacheContext {
	
	private static JedisCacheContext instance = new JedisCacheContext() ; 
	
	private IJedisHashCommand jedisHashCommand;
	
	private Converter converter ; 
	
	public static JedisCacheContext getInstance(){
		return instance ; 
	}

	public IJedisHashCommand getJedisHashCommand() {
		return jedisHashCommand;
	}

	public void setJedisHashCommand(IJedisHashCommand jedisHashCommand) {
		this.jedisHashCommand = jedisHashCommand;
	}

	public Converter getConverter() {
		if(converter == null){
			this.converter = new JavaConverter() ; 
		}
		return converter;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}
	
	
	
}
