package com.fei.common.log;

public class ServerManager {

	private static ServerManager instance = new ServerManager() ; 
	
	public static ServerManager getInstance(){
		return instance; 
	}
}
