package com.fei.common.rpc.api;

import com.fei.common.rpc.dto.CrawlerMonitorDto;
import com.fei.netty.springmvc.rpc.RpcInterface;
import com.fei.netty.springmvc.zookeeper.server.ServerGroupEnum;

@RpcInterface(ServerGroupEnum.CRAWLER)
public interface IAdminToCrawlerController {
	
	public CrawlerMonitorDto getCrawlerMonitor() ; 
	
	public void startCrawler() ;
	
	public void stopCrawler() ; 
	
}
