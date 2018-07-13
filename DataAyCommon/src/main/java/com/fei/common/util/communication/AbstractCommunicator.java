package com.fei.common.util.communication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fei.common.CallBack;
import com.fei.common.Response;

public abstract class AbstractCommunicator implements Communicator{
	
	private ExecutorService excutorService ; 
	
	private int threadNum = 5 ; 
	
	
	public void ini(){
		if(excutorService == null){
			excutorService = Executors.newFixedThreadPool(threadNum) ;
		}
	}
	
	@Override
	public void sendAync(Content content,CallBack callBack) { 
		excutorService.submit(new Runnable(){

			@Override
			public void run(){
				Response response = send(content);
				callBack.call(response);
			}
			
		});  
	}

	@Override
	public void sendAync(Content content) {
		sendAync(content,null);
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public ExecutorService getExcutorService() {
		return excutorService;
	}
	public void setExcutorService(ExecutorService excutorService) {
		this.excutorService = excutorService;
	}
	
	
}
