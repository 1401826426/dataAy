package com.fei.common.rpc.framework.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RpcByteRequest {
	
	private final static AtomicInteger REQUEST_ID = new AtomicInteger(1) ; 
	
	private int requestId ; 
	
	private URL url ; 
	
	private Object[] args ;

	private Object lock = new Object() ; 
	
	public RpcByteRequest(String url, Object[] args) throws MalformedURLException {
		super();
		this.requestId = REQUEST_ID.getAndIncrement() ; 
		this.url = new URL(url);
		this.args = args;
	}

	public int getRequestId() {
		return requestId;
	}

	public URL getUrl(){
		return this.url ; 
	}
	
	public String getHost(){
		return this.url.getHost() ; 
	}
	
	public int getPort(){
		return this.url.getPort() ; 
	}
	
	public byte[] packBytes(){
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			return mapper.writeValueAsBytes(args) ;
		} catch (JsonProcessingException e) {
			// TODO 打log
			e.printStackTrace();
		} 
		return null ; 
	}
	
	public Object getLock(){
		return this.lock ; 
	}
	
	
}






