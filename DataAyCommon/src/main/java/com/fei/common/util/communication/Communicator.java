package com.fei.common.util.communication;

import com.fei.common.CallBack;
import com.fei.common.Response;

public interface Communicator {
	
	public Response send(Content content) ; 
	
	public void sendAync(Content content) ;
	
	public void sendAync(Content content,CallBack callBack) ; 
	
}
