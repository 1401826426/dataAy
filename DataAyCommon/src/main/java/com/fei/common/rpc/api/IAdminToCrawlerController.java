package com.fei.common.rpc.api;

import com.fei.common.rpc.dto.CrawlerMonitorDto;
import com.fei.common.rpc.framework.RpcInterface;
import com.fei.common.zookeeper.server.ServerGroupEnum;

@RpcInterface(ServerGroupEnum.CRAWLER)
public interface IAdminToCrawlerController {
	
	public CrawlerMonitorDto getCrawlerMonitor() ; 
	
	public void startCrawler() ;
	
	public void stopCrawler() ; 
	
}
