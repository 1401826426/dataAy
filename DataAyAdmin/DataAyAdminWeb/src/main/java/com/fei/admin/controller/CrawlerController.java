package com.fei.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fei.admin.service.ICrawlerService;
import com.fei.common.Response;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {
	
	@Autowired
	private ICrawlerService crawlerService ; 
	
	@RequestMapping(method=RequestMethod.GET,value="/monitor")
	public Response getCrawlerMonitor(){
		return crawlerService.getCrawlerMonitor() ;
	}
	
	
}
