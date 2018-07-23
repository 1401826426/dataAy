package com.fei.common.rpc.framework.sender;

import com.fei.common.rpc.framework.common.RpcByteRequest;
import com.fei.common.rpc.framework.common.RpcByteResponse;

public interface Sender {
	
	public RpcByteResponse send(RpcByteRequest request) ; 
	
	public void sendAync(RpcByteRequest request,RpcSenderCallBack callBack) ; 
	
}
