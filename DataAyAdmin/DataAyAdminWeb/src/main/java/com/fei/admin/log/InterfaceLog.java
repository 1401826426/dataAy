package com.fei.admin.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

//@Aspect
//@Component
@SuppressWarnings("unused")
public class InterfaceLog {
	
	@Before("execution(* com.fei.admin.service..*.*(..))")
	public void before(JoinPoint jp){
		int t = 0 ; 
		t++ ; 
	}
	
	@After("execution(* com.fei.admin.service..*.*(..))")
	public void after(JoinPoint jp){
		int t = 0 ; 
		t++ ; 
	}

}
