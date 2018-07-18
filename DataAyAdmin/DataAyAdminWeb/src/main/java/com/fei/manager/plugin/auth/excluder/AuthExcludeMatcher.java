package com.fei.manager.plugin.auth.excluder;

import javax.servlet.http.HttpServletRequest;

public interface AuthExcludeMatcher {
	
	public boolean match(HttpServletRequest request,Object handler) ; 
	
}
