package com.fei.common;

public class Response {
	
	private ResponseStatus  status ; 
	
	private Object data ;

	public Response(ResponseStatus status) {
		this.status = status ; 
	}

	public Response(ResponseStatus status,Object data) {
		this.status = status ; 
		this.data = data ; 
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	} 
	
	public static Response ok(){
		return new Response(ResponseStatus.OK) ; 
	}
	
	public static Response fail(String msg){
		return new Response(ResponseStatus.FAIL , msg) ; 
	}
	
	public static Response ok(Object data){
		return new Response(ResponseStatus.OK , data) ; 
	}
	
}
















