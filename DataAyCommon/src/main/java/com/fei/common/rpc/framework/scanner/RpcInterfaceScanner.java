package com.fei.common.rpc.framework.scanner;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class RpcInterfaceScanner extends ClassPathBeanDefinitionScanner{

	public RpcInterfaceScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	protected void postProcessBeanDefinition(AbstractBeanDefinition beanDefinition, String beanName) {
		// TODO Auto-generated method stub
		super.postProcessBeanDefinition(beanDefinition, beanName);
	}
	
	
	
}
