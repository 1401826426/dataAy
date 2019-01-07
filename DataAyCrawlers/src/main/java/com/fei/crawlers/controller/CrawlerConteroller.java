package com.fei.crawlers.controller;

import org.springframework.stereotype.Component;

import com.fei.common.rpc.api.IAdminToCrawlerController;
import com.fei.common.rpc.dto.CrawlerMonitorDto;

@Component
public class CrawlerConteroller implements IAdminToCrawlerController{

	@Override
	public CrawlerMonitorDto getCrawlerMonitor() {
		CrawlerMonitorDto dto = new CrawlerMonitorDto() ; 
		dto.setThreadNum(1);
		return dto;
	}
	
	

}
