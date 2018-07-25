package com.fei.common.rpc.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import com.fei.common.rpc.api.TestRpc;
import com.fei.common.rpc.api.TestRpc.TestRpcAync;
import com.fei.common.rpc.dto.TestDto;
import com.fei.common.rpc.framework.converter.Converter;
import com.fei.common.rpc.framework.converter.fackson.FackJsonConverter;
import com.fei.common.rpc.framework.generator.RpcMethodUrlGenerator;
import com.fei.common.rpc.framework.generator.UrlGenerator;
import com.fei.common.rpc.framework.proxy.RpcInterfaceProxyHandler;
import com.fei.common.rpc.framework.sender.Sender;
import com.fei.common.rpc.framework.sender.netty.NettySender;

public class RpcInterfaceFactory {
	
	private Sender sender  ; 
	
	private UrlGenerator urlGenerator ; 
	
	private Converter converter ;
	
	private InvocationHandler handler ; 

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public UrlGenerator getUrlGenerator() {
		return urlGenerator;
	}

	public void setUrlGenerator(UrlGenerator urlGenerator) {
		this.urlGenerator = urlGenerator;
	}

	public Converter getConverter() {
		return converter;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	} 
	
	@SuppressWarnings("unchecked")
	public <T> T getRpcInterface(Class<T> clazz){
		if(clazz == null || !(clazz.isInterface())){
			throw new RuntimeException("clazz为空或者不是接口") ; 
		}
		iniHandler() ; 
		return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this.handler) ;  
	}

	private void iniHandler() {
		if(this.handler == null){
			checkAllIni();  
			this.handler = new RpcInterfaceProxyHandler(sender,urlGenerator,converter) ;
		}
	}

	private void checkAllIni() {
		if(sender == null){
			NettySender nettySender = new NettySender() ; 
			nettySender.ini();  
			this.sender = nettySender; 
		}
		if(converter == null){
			this.converter = new FackJsonConverter() ;  
		}
		if(this.urlGenerator == null){
			this.urlGenerator = new RpcMethodUrlGenerator() ; 
		}
	}
	
	public static void main(String[] args) {
		RpcInterfaceFactory factory = new RpcInterfaceFactory(); 
		TestRpc testRpc = factory.getRpcInterface(TestRpc.class) ;
		TestDto testDto = new TestDto() ; 
		testDto.setName("name");
		testDto.setPassword("password");
		List<TestDto> list = new ArrayList<TestDto>() ;
		list.add(testDto) ; 
		System.err.print(testRpc.test(list)) ;
		System.err.println(testRpc.testOne(testDto));
		
		TestRpcAync testRpcAync = factory.getRpcInterface(TestRpcAync.class) ; 
		testRpcAync.test(list, new RpcCallBack<List<TestDto>>() {
			
			@Override
			public void success(List<TestDto> obj) {
				System.err.println(obj);
			}
			
			@Override
			public void error(Exception e) {
				e.printStackTrace();
			}
		});
	}
}
