package com.fei.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fei.admin.mapper.UserMapper;
import com.fei.admin.pojo.User;
import com.fei.admin.pojo.UserExample;
import com.fei.admin.service.ITestService;
import com.fei.common.Response;

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






























