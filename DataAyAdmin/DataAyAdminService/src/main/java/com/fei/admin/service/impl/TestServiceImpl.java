package com.fei.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fei.admin.mapper.UserMapper;
import com.fei.admin.pojo.User;
import com.fei.admin.pojo.UserExample;
import com.fei.admin.service.ITestService;
import com.fei.admin.service.ITxTestService;
import com.fei.common.Response;
import com.fei.common.rpc.api.TestRpc;
import com.fei.common.rpc.dto.TestDto;

@Service
public class TestServiceImpl implements ITestService{
	
	@Autowired
	private UserMapper userMapper ;

	@Autowired
	private TestRpc testRpc ;
	
	@Autowired
	private ITxTestService txTestService  ;
	
	@Override
	public Response test() {
		UserExample example = new UserExample() ; 
		List<User> users = userMapper.selectByExample(example)  ;
		return Response.ok(users);
	}

	@Override
	public TestDto testSpring() {
		TestDto testDto = new TestDto(); 
		testDto.setName("spring") ; 
		testDto.setPassword("password"); 
		TestDto result = testRpc.testOne(testDto) ; 
		return result;
	}

	@Override
	@Transactional
	public Response testTx() {
		User user1 = new User() ;
		user1.setName("test11");
		userMapper.insert(user1) ;
		User user2 = new User() ;
		int t = 1/0 ; 
		user2.setTelephone(String.valueOf(t));
		user2.setName("test2") ;
		userMapper.insert(user2) ;
		return Response.ok();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Response testReequiresNew() {
		User user1 = new User() ; 
		user1.setName("REQUIRES_NEW1");
		userMapper.insert(user1) ;
		txTestService.nested();  
		User user3 = new User() ;
		user3.setName("REQUIRES_NEW3");
		userMapper.insert(user3) ; 
		return Response.ok();
	} 
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Response nested(){
		User user2 = new User() ;
		user2.setName("REQUIRES_NEW2");
		userMapper.insert(user2); 
		return Response.ok() ; 
	}
	
	
}






























