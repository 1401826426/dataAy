package com.fei.common.zookeeper.server;

public class ServerKeyGenerator {
	
	public static String generate(Server server){
//		ServerGroupEnum group = server.getGroup() ; 
//		int key = (group.getId() << 16) + server.getId(); 
//		return String.valueOf(key) ; 
		return server.getGroup().str() + "_" + server.getId() ; 
	}
	
	
	
}
