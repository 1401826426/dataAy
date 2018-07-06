package com.fei;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("unused")
public class TestMybatis {
	
	
	private BeanFactory beanFactory ; 
	
	private final static String CONTEXT_PATH = "" ; 
	
	@Before
	public void init(){
		beanFactory = new ClassPathXmlApplicationContext(CONTEXT_PATH) ; 
	}
	
	public void test(){
		
	}
	
	
}
