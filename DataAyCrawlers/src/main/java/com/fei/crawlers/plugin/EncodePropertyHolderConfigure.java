package com.fei.crawlers.plugin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.fei.common.util.encode.Coder;
import com.fei.common.util.encode.CoderFactory;

public class EncodePropertyHolderConfigure extends PropertyPlaceholderConfigurer{
	
	private static Set<String> keys = new HashSet<String>() ; 
	
	static{
		
	}
	
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if(keys.contains(propertyName)){
			Coder coder = CoderFactory.getInstance().getAesCoder() ; 
			return coder.decode(propertyValue) ; 
		}else{
			return super.convertProperty(propertyName, propertyValue);			
		}
	}
	
	
	
	
}
