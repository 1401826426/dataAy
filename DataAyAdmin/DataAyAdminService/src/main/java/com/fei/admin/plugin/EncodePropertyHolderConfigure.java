package com.fei.admin.plugin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.fei.common.util.encode.Coder;
import com.fei.common.util.encode.CoderFactory;

public class EncodePropertyHolderConfigure extends PropertyPlaceholderConfigurer{
	
	private static Set<String> keys = new HashSet<String>() ; 
	
	static{
		keys.add("jdbc.password") ; 
		keys.add("telephone.aliyun.accessKeyId") ; 
		keys.add("telephone.aliyun.accessKeySecret") ; 
		keys.add("telephone.aliyun.signName") ;
		keys.add("mail.from") ;
		keys.add("mail.from.password") ;
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
