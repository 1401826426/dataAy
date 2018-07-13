package com.fei.common.util.encode;

public class CoderFactory {
	
	public final static CoderFactory instance = new CoderFactory() ; 
	
	public static CoderFactory getInstance(){
		return instance ; 
	}
	
	private CoderFactory(){
		
	}
	
	public Coder getMd5Coder(){
		return new Md5Coder() ; 
	}
	
	public Coder getAesCoder(){
		return new AesCoder() ; 
	}
}
