package com.fei.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fei.admin.service.ITestService;
import com.fei.common.Response;

@Controller
public class TestController {
	
	
	@Autowired
	private ITestService testService ; 
	
	@RequestMapping(value = "/test" , produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Response test(){ 
		return testService.test(); 
	}
	
	
	
}
