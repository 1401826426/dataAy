package com.fei.admin.plugin.auth;

import java.util.HashMap;
import java.util.Map;

public class AuthConstants {
	
	public final static String USER_DTO_ATTRIBUTE = "com.fei.auth.userDto" ;
	
	public static Map<String,Integer> METHOD_AUTH = new HashMap<String,Integer>() ; 
	
	static{
		METHOD_AUTH.put("GET",1) ;
		METHOD_AUTH.put("PUT",1<<1) ;
		METHOD_AUTH.put("POST",1<<2) ;
		METHOD_AUTH.put("DELETE",1<<3) ;
	}
	
	
}
