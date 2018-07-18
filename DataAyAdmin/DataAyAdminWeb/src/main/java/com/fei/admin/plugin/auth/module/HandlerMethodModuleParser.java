package com.fei.admin.plugin.auth.module;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.method.HandlerMethod;

public class HandlerMethodModuleParser implements ModuleParser{

	@Override
	public ModuleEnum parseModule(HttpServletRequest request, Object handler) {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod)handler ;
			Module module = handlerMethod.getMethodAnnotation(Module.class) ;
			if(module != null){
				return module.value() ; 
			}
			module = handlerMethod.getBeanType().getAnnotation(Module.class) ;
			if(module != null){
				return module.value() ; 
			}
		}
		return null;
	}

	@Override
	public boolean support(Object handler) {
		return handler instanceof HandlerMethod ; 
	}

}
