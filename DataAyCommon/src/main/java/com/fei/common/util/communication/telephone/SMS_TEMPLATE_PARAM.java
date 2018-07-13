package com.fei.common.util.communication.telephone;

public enum SMS_TEMPLATE_PARAM {
	
	CODE("code") , 
	
	PASSWORD("password") , 
	
	PRODUCT("product") , 
	;
	
	private String name ; 
	
	private SMS_TEMPLATE_PARAM(String name){
		this.name = name ;  
	}
	
	public String getName(){
		return name; 
	}
	
}
