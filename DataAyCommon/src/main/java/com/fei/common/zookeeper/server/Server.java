package com.fei.common.zookeeper.server;

public class Server {
	
	private String host ; 
	
	private int port ; 
	
	private ServerGroupEnum group ;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ServerGroupEnum getGroup() {
		return group;
	}

	public void setGroup(ServerGroupEnum group) {
		this.group = group;
	}  
	
	
}
