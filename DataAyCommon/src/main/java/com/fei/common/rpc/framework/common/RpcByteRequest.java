package com.fei.common.rpc.framework.common;

import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicInteger;

public class RpcByteRequest {
	
	private final static AtomicInteger REQUEST_ID = new AtomicInteger(1) ; 
	
	private int requestId ; 
	
	private byte[] content ; 

	private Object lock = new Object() ; 
	
	private String path ; 
	
	public RpcByteRequest(String path, byte[] content) throws MalformedURLException {
		super();
		this.requestId = REQUEST_ID.getAndIncrement() ; 
		this.path = path ; 
		this.content = content ;
	}

	public int getRequestId() {
		return requestId;
	}
	
	public String getHost(){
		return "localhost" ;  
	}
	
	public int getPort(){
		return 8080 ;  
	}
	
	
	public String getPath(){
		return path ; 
	}
	
	public byte[] packBytes(){
		 return content ; 
	}
	
	public Object getLock(){
		return this.lock ; 
	}
	
	
}






