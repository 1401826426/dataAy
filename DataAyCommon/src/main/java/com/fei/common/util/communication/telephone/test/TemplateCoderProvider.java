//package com.fei.common.util.communication.telephone.test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class TemplateCoderProvider {
//	
//	public enum TEMPLATE_CODER{
//		SIGN("sign"),CHANGE_PASSWORD("changePassword"), ; 
//		private String name ; 
//		private TEMPLATE_CODER(String name){
//			this.name = name ; 
//		}
//		public String getName(){
//			return name ; 
//		}
//	}
//	
//	private Map<String,String> templateCodes = new HashMap<>();
//	
//	public void addTemplate(String codeName,String code){
//		templateCodes.put(codeName, code) ; 
//	}
//	
//	
//	public String getCode(String codeName){
//		return templateCodes.get(codeName) ; 
// 	}
//	
//	public String getCode(TEMPLATE_CODER coder){
//		return getCode(coder.getName()) ; 
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
