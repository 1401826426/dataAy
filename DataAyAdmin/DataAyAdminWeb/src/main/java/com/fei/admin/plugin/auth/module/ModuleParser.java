package com.fei.admin.plugin.auth.module;

import javax.servlet.http.HttpServletRequest;

public interface ModuleParser{
	
	public ModuleEnum  parseModule(HttpServletRequest request,Object handler) ; 
	
	public boolean support(Object handler) ; 
	
}
