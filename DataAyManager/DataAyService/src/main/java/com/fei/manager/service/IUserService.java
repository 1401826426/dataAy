package com.fei.manager.service;

import com.fei.common.Response;
import com.fei.manager.pojo.User;

public interface IUserService {
	
	public Response login(String name,String password) ; 
	
	public Response register(User user) ; 
	
}
