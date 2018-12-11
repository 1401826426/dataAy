package com.fei.crawlers.controller;

import com.fei.common.rpc.api.IAdminToCrawlerController;
import com.fei.common.rpc.dto.CrawlerMonitorDto;
import com.fei.netty.springmvc.deprecated.RpcController;

@RpcController
public class CrawlerConteroller implements IAdminToCrawlerController{

	@Override
	public CrawlerMonitorDto getCrawlerMonitor() {
		CrawlerMonitorDto dto = new CrawlerMonitorDto() ; 
		dto.setThreadNum(1);
		return dto;
	}

	@Override
	public void startCrawler() {
		
		
	}

	@Override
	public void stopCrawler() {
		
	}
	
	

}
