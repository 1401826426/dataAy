package com.fei.common.rpc.framework;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class RpcInterfaceScanner extends ClassPathBeanDefinitionScanner{

	public RpcInterfaceScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

}
