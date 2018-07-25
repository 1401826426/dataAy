package com.fei.common.rpc.framework;

public interface RpcCallBack<T>{
	
	public void success(T obj); 
	
	public void error(Exception e) ; 
	
}
