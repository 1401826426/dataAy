package com.fei.common.rpc.framework.generator;

import java.lang.reflect.Method;

import util.str.StringUtils;


//对于rpc method的产生
//用其对应接口加对应method的方式
//然后用大写字母隔开
public class RpcMethodUrlGenerator {
	
	public String generate(Method method){
		String name = method.getName() ;
		String className = method.getDeclaringClass().getSimpleName() ; 
		String[] classNames = StringUtils.splitByUpper(className) ; 
		String[] methodNames = StringUtils.splitByUpper(name) ; 
		StringBuilder sb = new StringBuilder("") ;
		boolean flag = false ; 
		for(String na:classNames){
			if(flag){				
				sb.append("/") ; 
			}
			sb.append(na.toLowerCase()) ;
			flag = true ; 
		}
		for(String na:methodNames){
			if(flag){
				sb.append("/") ; 
			}
			sb.append(na.toLowerCase())  ; 
			flag = true ; 
		}
		return sb.toString(); 
	}
	
}
