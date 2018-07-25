package com.fei.common.rpc.api;

import java.util.List;

import com.fei.common.rpc.dto.TestDto;
import com.fei.common.rpc.framework.RpcCallBack;
import com.fei.common.rpc.framework.RpcInterface;

@RpcInterface
public interface TestRpc {
	
	public List<TestDto> test(List<TestDto> testDto) ;
	
	public TestDto testOne(TestDto testDto) ; 
	
	public interface TestRpcAync extends TestRpc{
		
		public void test(List<TestDto> testDto,RpcCallBack<List<TestDto>> callBack) ; 
		
	}
	
}
