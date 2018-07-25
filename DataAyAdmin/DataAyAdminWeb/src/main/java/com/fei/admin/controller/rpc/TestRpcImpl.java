package com.fei.admin.controller.rpc;

import java.util.List;

import com.fei.common.rpc.api.TestRpc;
import com.fei.common.rpc.dto.TestDto;
import com.fei.common.rpc.framework.mvc.RpcController;

@RpcController
public class TestRpcImpl implements TestRpc{

	@Override
	public List<TestDto> test(List<TestDto> testDto) {
		return testDto;
	}

	@Override
	public TestDto testOne(TestDto testDto) {
		return testDto;
	}

}
