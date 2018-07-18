package com.fei.common;

public class Response {
	
	private int status ; 
	
	private Object data ;
	
	private String msg ; 
	

	public Response(int status) {
		this.status = status ; 
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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
		Response response = new Response(ResponseStatus.FAIL.state()) ; 
		response.setMsg(msg);
		return response ;  
	}
	
	public static Response ok(Object data){
		Response response = new Response(ResponseStatus.OK.state()) ;
		response.setData(data);
		return response ;  
	}
	
}
















