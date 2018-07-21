package com.fei.common.rpc.framework;

import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RpcByteRequest {
	
	private int requestId ; 
	
	private URL url ; 
	
	private Object[] args ;

	public RpcByteRequest(int requestId, String url, Object[] args) throws MalformedURLException {
		super();
		this.requestId = requestId;
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
	
	
	
}






