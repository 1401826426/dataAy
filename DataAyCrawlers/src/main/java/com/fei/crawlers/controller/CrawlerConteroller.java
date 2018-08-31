package com.fei.crawlers.controller;

import com.fei.common.rpc.api.IAdminToCrawlerController;
import com.fei.common.rpc.dto.CrawlerMonitorDto;
import com.fei.common.rpc.framework.spring.mvc.RpcController;

@RpcController
public class CrawlerConteroller implements IAdminToCrawlerController{

	@Override
	public CrawlerMonitorDto getCrawlerMonitor() {
		CrawlerMonitorDto dto = new CrawlerMonitorDto() ; 
		dto.setThreadNum(1);
		return dto;
	}

}
