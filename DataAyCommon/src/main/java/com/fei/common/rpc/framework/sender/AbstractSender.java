package com.fei.common.rpc.framework.sender;

import com.fei.common.asyn.SynExecuteSupport;
import com.fei.common.rpc.framework.common.RpcByteRequest;
import com.fei.common.rpc.framework.common.RpcByteResponse;

public abstract class AbstractSender extends SynExecuteSupport implements Sender{

	@Override
	public void sendAync(RpcByteRequest request, RpcSenderCallBack callBack) {
		getExecutorService().execute(new Runnable() {
			
			@Override
			public void run() {
				try{
					RpcByteResponse response = send(request) ;
					if(callBack != null){						
						callBack.success(response);
					}
				}catch (Exception e) {
					if(callBack != null){
						callBack.error(e);						
					}
				}
				
			}
		});
	}
	
	
	
	
}
