package com.fei.common.rpc.framework;

public interface RpcCallBack{
	
	public void success(Object obj); 
	
	public void error(Exception e) ; 
	
}
