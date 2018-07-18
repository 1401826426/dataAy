package com.fei.admin.service;

import com.fei.admin.pojo.User;
import com.fei.common.Response;

public interface IUserService {
	
	public Response login(User user) ; 
	
	public Response register(User user) ; 
	
}
