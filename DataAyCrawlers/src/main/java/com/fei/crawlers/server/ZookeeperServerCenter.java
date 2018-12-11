package com.fei.crawlers.server;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fei.netty.springmvc.zookeeper.AbstractZookeeperServerCenter;
import com.fei.netty.springmvc.zookeeper.server.Server;
import com.fei.netty.springmvc.zookeeper.server.ServerGroupEnum;

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
	
	private Server selfServer ; 
	
	@Override
	protected Server loadServer() {
		selfServer = new Server(ServerGroupEnum.CRAWLER,host,Integer.valueOf(port),Integer.valueOf(id)) ;
		return selfServer;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.ini();  
	}

	@Override
	protected String loadConnectString() {
		return zkConnectString;
	}

	@Override
	public Server getSelfServer() {
		return selfServer;
	}


}
