package com.fei.admin.service;

import com.fei.common.Response;
import com.fei.common.rpc.dto.TestDto;

public interface ITestService {
	
	public Response test() ;

	public TestDto testSpring();

	public Response testTx();

	public Response testReequiresNew(); 
	
	public Response nested(); 
	
}
