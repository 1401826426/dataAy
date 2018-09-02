package com.fei.admin.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fei.admin.server.ZookeeperServerCenter;
import com.fei.admin.service.BaseService;
import com.fei.admin.service.ICrawlerService;
import com.fei.common.Response;
import com.fei.common.rpc.api.IAdminToCrawlerController;
import com.fei.common.rpc.dto.CrawlerMonitorDto;
import com.fei.common.rpc.framework.RpcInterfaceFactory;
import com.fei.common.zookeeper.server.Server;
import com.fei.common.zookeeper.server.ServerGroupEnum;

@Service
public class CrawlerService extends BaseService implements ICrawlerService{
	
	@Autowired
	private RpcInterfaceFactory rpcInterfaceFactory ; 
	
	@Autowired
	private ZookeeperServerCenter zookeeperServerCenter ; 
	
	@Override
	public Response getCrawlerMonitor() {
		Collection<Server> servers = zookeeperServerCenter.getServerByGroupType(ServerGroupEnum.CRAWLER) ;
		List<CrawlerMonitorDto> list = new ArrayList<>() ; 
		if(servers != null) {
			for(Server server:servers){
				IAdminToCrawlerController crawlerController = rpcInterfaceFactory.getRpcInterface(IAdminToCrawlerController.class, server) ; 
				CrawlerMonitorDto dto = crawlerController.getCrawlerMonitor() ; 
				list.add(dto) ; 
			}
		}
		return Response.ok(list); 
	}
	
	
	
}
