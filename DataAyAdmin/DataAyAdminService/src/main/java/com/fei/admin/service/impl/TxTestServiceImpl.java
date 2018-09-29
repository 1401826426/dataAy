package com.fei.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fei.admin.mapper.UserMapper;
import com.fei.admin.pojo.User;
import com.fei.admin.service.ITxTestService;

@Service
public class TxTestServiceImpl implements ITxTestService{
	
	@Autowired
	private UserMapper userMapper ;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void nested() {
		User user2 = new User() ;
		user2.setName("REQUIRES_NEW2");
		userMapper.insert(user2); 
	}

}
