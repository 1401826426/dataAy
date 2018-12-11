package com.fei.common.rpc.api;

import java.util.List;

import com.fei.common.rpc.dto.TestDto;
import com.fei.netty.springmvc.rpc.RpcCallBack;
import com.fei.netty.springmvc.rpc.RpcInterface;
import com.fei.netty.springmvc.zookeeper.server.ServerGroupEnum;

@RpcInterface(ServerGroupEnum.ADMIN)
public interface TestRpc {
	
	public List<TestDto> test(List<TestDto> testDto) ;
	
	public TestDto testOne(TestDto testDto) ; 
	
	public interface TestRpcAync extends TestRpc{
		
		public void test(List<TestDto> testDto,RpcCallBack<List<TestDto>> callBack) ; 
		
	}
	
}
