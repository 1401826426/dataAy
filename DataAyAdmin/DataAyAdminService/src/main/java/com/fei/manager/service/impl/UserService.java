package com.fei.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fei.common.Response;
import com.fei.common.util.encode.Coder;
import com.fei.common.util.encode.CoderFactory;
import com.fei.manager.mapper.UserMapper;
import com.fei.manager.pojo.User;
import com.fei.manager.pojo.UserExample;
import com.fei.manager.pojo.UserExample.Criteria;
import com.fei.manager.service.IUserService;

import util.str.StringUtils;

@Service("userService")
public class UserService implements IUserService{
	
	@Autowired
	private UserMapper userMapper ; 
	
//	@Autowired
//	private TelephoneCommunicator telephoneCommunicator ; 
	
//	@Autowired
//	private MailCommunicator mailCommunicator ; 
	
	@Override
	public Response login(User user) {
		String name = user.getName() ; 
		String password = user.getPassword() ; 
		if(StringUtils.isBlank(user.getName())){
			return Response.fail("name不能为空") ; 
		}
		if(StringUtils.isBlank(user.getPassword())){
			return Response.fail("password不能为空") ; 
		}
		Coder coder = CoderFactory.getInstance().getMd5Coder()  ;
		String dPass = coder.encode(password); 
		UserExample example = new UserExample() ; 
		Criteria criteria = example.createCriteria() ; 
		criteria.andNameEqualTo(name); 
		List<User> users = userMapper.selectByExample(example) ;
		if(users == null || users.size() < 1){
			return Response.fail("没有name为"+name+"的玩家") ; 
		}
		User userInDB = users.get(0) ; 
		if(!dPass.equals(userInDB.getPassword())){
			return Response.fail("密码错误") ; 
		}
		return Response.ok(userInDB);
	}

	@Override
	public Response register(User user) {
		String name = user.getName(); 
		if(StringUtils.isBlank(name)){
			return Response.fail("名字不能为空") ; 
		}
		if(StringUtils.isBlank(user.getPassword())){
			return Response.fail("密码不能为空") ;
		}
		Coder coder = CoderFactory.getInstance().getMd5Coder() ; 
		String newPass = coder.encode(user.getPassword()) ;
		user.setPassword(newPass);
		user.setRoleGroup(0);
		userMapper.insert(user) ; 
		return Response.ok(user);
	}



}
















