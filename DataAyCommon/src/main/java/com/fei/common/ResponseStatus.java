package com.fei.common;

public enum ResponseStatus {
	
	FAIL(0), 
	
	OK(1) , 
	;
	
	private int status; 
	
	private ResponseStatus(int status){
		this.status=status ; 
	}
	
	public int state(){
		return status ; 
	}
}
