package com.fei.common.rpc.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.fei.common.rpc.framework.RpcCallBack;
import com.fei.common.rpc.framework.common.RpcByteRequest;
import com.fei.common.rpc.framework.common.RpcByteResponse;
import com.fei.common.rpc.framework.converter.Converter;
import com.fei.common.rpc.framework.converter.ConverterException;
import com.fei.common.rpc.framework.generator.RpcMethodUrlGenerator;
import com.fei.common.rpc.framework.sender.RpcSenderCallBack;
import com.fei.common.rpc.framework.sender.Sender;

public class RpcInterfaceProxyHandler implements InvocationHandler{
	
	private Sender sender; 
	
	private RpcMethodUrlGenerator generator ;
	
	private Converter converter ; 
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String url = generator.generate(method) ; 
		RpcByteRequest request = new RpcByteRequest(url, args) ; 
		RpcSenderCallBack callBack = null ; 
		Type type = method.getGenericReturnType()  ;
		if(args.length > 0){
			Object call = args[args.length-1] ; 
			if(call != null && call instanceof RpcCallBack){
				RpcCallBack rpcCallBack = (RpcCallBack)call ; 
				callBack = new RpcSenderCallBack() {
					@Override
					public void success(RpcByteResponse response) {
						try {
							Object obj = converter.readValue(response.getData(), type) ;
							rpcCallBack.success(obj);
						} catch (ConverterException e) {
							rpcCallBack.error(e);
						}	
					}
					@Override
					public void error(Exception e) {
						rpcCallBack.error(e);
					}
				};
			}
		}
		if(callBack != null){
			sender.sendAync(request, callBack);
			return null ; 
		}else{
			RpcByteResponse response = sender.send(request) ; 
			Object obj = converter.readValue(response.getData(), type) ;
			return obj;
		}
	}
	
}
