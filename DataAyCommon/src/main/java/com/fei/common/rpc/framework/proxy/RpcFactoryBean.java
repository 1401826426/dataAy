package com.fei.common.rpc.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

@SuppressWarnings("unchecked")
public class RpcFactoryBean<T> implements FactoryBean<T>{
	
	private Class<T> rpcInterface ; 
	
	public RpcFactoryBean(Class<T> rpcInterface){
		this.rpcInterface = rpcInterface ; 
	}
	
	
	@Override
	public T getObject() throws Exception {
		return (T) Proxy.newProxyInstance(rpcInterface.getClassLoader(),new Class[]{rpcInterface},getInvocationHandler());
	}

	private InvocationHandler getInvocationHandler() {
		return new RpcInterfaceProxyHandler();
	}

	@Override
	public Class<?> getObjectType() {
		return this.rpcInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
