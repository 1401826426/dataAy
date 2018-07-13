package com.fei.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fei.common.Response;
import com.fei.common.util.encode.Coder;
import com.fei.common.util.encode.CoderFactory;
import com.fei.manager.mapper.UserMapper;
import com.fei.manager.pojo.User;
import com.fei.manager.pojo.UserExample;
import com.fei.manager.pojo.UserExample.Criteria;
import com.fei.manager.service.IUserService;

import util.str.StringUtils;

public class UserService implements IUserService{
	
	@Autowired
	private UserMapper userMapper ; 
	
	@Override
	public Response login(String name, String password) {
		if(StringUtils.isBlank(name)){
			return Response.fail("name不能为空") ; 
		}
		if(StringUtils.isBlank(password)){
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
		User user = users.get(0) ; 
		if(!dPass.equals(user.getPassword())){
			return Response.fail("密码错误") ; 
		}
		return Response.ok(user);
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
		if(!StringUtils.isBlank(user.getMail())){
			if(!StringUtils.isMail(user.getMail())){
				return Response.fail("邮箱格式不对") ; 
			}
		}
		if(!StringUtils.isBlank(user.getTelephone())){
			if(!StringUtils.isTelephone(user.getTelephone())){
				return Response.fail("手机格式不对");
			}
		}
		return null;
	}

}
















