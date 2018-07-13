package com.fei.common.util.communication.telephone;

public enum SMS_TEMPLATE_CODE {
	
	SIGN("sign") ,
	
	CHANGE_PASSWORD("changePassword") , 
	
	SUBSCRIPTION_NOTICE("subscriptionNotice") , 
	;
	private String name ; 
	
	private SMS_TEMPLATE_CODE(String name){
		this.name= name ; 
	}
	
	public String getName(){
		return this.name ; 
	}
	
}
