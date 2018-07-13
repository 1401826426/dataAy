package com.fei.common.util.communication.telephone.content;

import java.util.HashMap;
import java.util.Map;

public class TemplateCoderProvider {
	
	private static Map<String,String> templateCodes = new HashMap<String,String>(); 
	
	public static String getCode(String name){
		return templateCodes.get(name) ; 
	}

	public Map<String, String> getTemplateCodes() {
		return templateCodes;
	}	
	
	public void setTemplateCodes(Map<String,String> map){
		templateCodes.putAll(map) ; 
	}
}
