package com.fei.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fei.common.Response;
import com.fei.manager.pojo.User;

@Controller
public class UserController {
	
	@RequestMapping(value={"/login"},method=RequestMethod.PUT)
	public Response login(String name,String password){
		return Response.ok() ; 
	}
	
	@RequestMapping(value={"/login"},method=RequestMethod.PUT)
	public Response register(User user){
		return Response.ok() ; 
	}
	
}
