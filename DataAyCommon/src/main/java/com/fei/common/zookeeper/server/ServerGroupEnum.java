package com.fei.common.zookeeper.server;

public enum ServerGroupEnum {
	
	ADMIN("admin"), 
	;
	
	private String str ; 
	
	private ServerGroupEnum(String str){
		this.str = str ; 
	}
	
	public String str(){
		return str ; 
	}
}
