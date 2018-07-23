package com.fei.common.rpc.framework.sender;

import com.fei.common.rpc.framework.common.RpcByteResponse;

public interface RpcSenderCallBack {
	
	public void success(RpcByteResponse response) ;
	
	public void error(Exception e) ; 
	
}
