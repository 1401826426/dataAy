package com.fei.crawlers.server;

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
		Server server = new Server(ServerGroupEnum.CRAWLER,host,Integer.valueOf(port),Integer.valueOf(id)) ; 
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


}
