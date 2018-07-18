package com.fei.manager.service;

import com.fei.common.Response;
import com.fei.manager.pojo.User;

public interface IUserService {
	
	public Response login(User user) ; 
	
	public Response register(User user) ; 
	
}
