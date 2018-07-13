package com.fei.common;

public class Response {
	
	private int status ; 
	
	private Object data ;

	public Response(int status) {
		this.status = status ; 
	}

	public Response(int status,Object data) {
		this.status = status ; 
		this.data = data ; 
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	} 
	
	public static Response ok(){
		return new Response(ResponseStatus.OK.state()) ; 
	}
	
	public static Response fail(String msg){
		return new Response(ResponseStatus.FAIL.state(), msg) ; 
	}
	
	public static Response ok(Object data){
		return new Response(ResponseStatus.OK.state(), data) ; 
	}
	
}
















