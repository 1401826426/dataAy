package com.fei.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fei.common.Response;
import com.fei.manager.mapper.UserMapper;
import com.fei.manager.pojo.User;
import com.fei.manager.pojo.UserExample;
import com.fei.manager.service.ITestService;

@Service
public class TestServiceImpl implements ITestService{
	
	@Autowired
	private UserMapper userMapper ;

	@Override
	public Response test() {
		UserExample example = new UserExample() ; 
		List<User> users = userMapper.selectByExample(example)  ;
		return Response.ok(users);
	} 
	
	
}






























