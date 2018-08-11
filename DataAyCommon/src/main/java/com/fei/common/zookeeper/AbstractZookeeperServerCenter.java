package com.fei.common.zookeeper;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fei.common.rpc.framework.converter.Converter;
import com.fei.common.rpc.framework.converter.ConverterException;
import com.fei.common.zookeeper.server.Server;
import com.fei.common.zookeeper.server.ServerGroupEnum;

import util.collection.HashMapArrayListMultiMap;

public class AbstractZookeeperServerCenter extends AbstractZookeeperCenter {

	private HashMapArrayListMultiMap<ServerGroupEnum, Server> servers;

	private Map<String, Server> serverMap;

	private String parentPath;

	private Converter converter ; 
	
	public void registerSelf(Server server) {

	}

	@Override
	protected void refresh() {
		servers = new HashMapArrayListMultiMap<>();
		serverMap = new HashMap<>();
		registerServers();
	}

	private void registerServers() {
		List<String> childs = getChild(parentPath);
		if(childs != null){
			for (String child : childs) {
				registerGroupServers(child);
			}
		}
	}

	@SuppressWarnings("unused")
	private void registerGroupServers(String child) {
		List<String> serverPaths = getChild(child);
		for(String serverPath:serverPaths){
			byte[] bytes = getData(serverPath) ; 
			try {
				Server server = (Server) converter.readValue(bytes, Server.class) ;
				//TODO
			} catch (ConverterException e) {
				e.printStackTrace();
			} 
		}
	}


	public Collection<Server> getServerByGroupType(ServerGroupEnum serverGroup) {
		return servers.get(serverGroup);
	}

	public Server getServerByKey(String serverKey) {
		return serverMap.get(serverKey);
	}

}
