package com.fei.admin.server;

import java.util.Collection;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fei.common.zookeeper.AbstractZookeeperServerCenter;
import com.fei.common.zookeeper.server.Server;
import com.fei.common.zookeeper.server.ServerGroupEnum;

@Component
public class ZookeeperServerCenter extends AbstractZookeeperServerCenter implements InitializingBean{
	
	@Value("${server.host}")
	private String host ; 
	
	@Value("${server.port}")
	private String port ; 
	
	@Value("${server.id}")
	private String id ;
	
	@Value("${zk.connect.string}")
	private String zkConnectString ; 
	
	@Override
	protected Server loadServer() {
		Server server = new Server(ServerGroupEnum.ADMIN,host,Integer.valueOf(port),Integer.valueOf(id)) ; 
		return server;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.ini();  
	}

	@Override
	protected String loadConnectString() {
		return zkConnectString;
	}
	
	public static void main(String[] args) throws Exception {
		ZookeeperServerCenter zookeeperServerCenter = new ZookeeperServerCenter() ; 
		zookeeperServerCenter.host = "localhost" ; 
		zookeeperServerCenter.port = "8080" ; 
		zookeeperServerCenter.id = "1" ; 
		zookeeperServerCenter.zkConnectString = "localhost:2181" ; 
		zookeeperServerCenter.afterPropertiesSet(); 
		Collection<Server> servers = zookeeperServerCenter.getServerByGroupType(ServerGroupEnum.CRAWLER) ; 
		for(Server server:servers){
			System.out.println(server);
		}
	}


}
