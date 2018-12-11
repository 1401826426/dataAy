package com.fei.admin.service.impl;

import org.springframework.stereotype.Service;

import com.fei.admin.service.BaseService;
import com.fei.admin.service.ICrawlerService;
import com.fei.common.Response;

@Service
public class CrawlerService extends BaseService implements ICrawlerService{
	
//	@Autowired
//	private RpcInterfaceFactory rpcInterfaceFactory ; 
	
//	@Autowired
//	private ZookeeperServerCenter zookeeperServerCenter ; 
	
	@Override
	public Response getCrawlerMonitor() {
//		Collection<Server> servers = zookeeperServerCenter.getServerByGroupType(ServerGroupEnum.CRAWLER) ;
//		List<CrawlerMonitorDto> list = new ArrayList<>() ; 
//		if(servers != null) {
//			for(Server server:servers){
//				IAdminToCrawlerController crawlerController = rpcInterfaceFactory.getRpcInterface(IAdminToCrawlerController.class, server) ; 
//				CrawlerMonitorDto dto = crawlerController.getCrawlerMonitor() ; 
//				list.add(dto) ; 
//			}
//		}
//		return Response.ok(list);
		return null ; 
	}
	
	
	
}
