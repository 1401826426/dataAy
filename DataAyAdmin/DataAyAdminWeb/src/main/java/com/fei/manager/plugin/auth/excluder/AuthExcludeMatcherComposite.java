package com.fei.manager.plugin.auth.excluder;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.collection.CollectionUtils;

public class AuthExcludeMatcherComposite implements AuthExcludeMatcher{
	
	private List<AuthExcludeMatcher> matchers ; 
	
	@Override
	public boolean match(HttpServletRequest request, Object handler) {
		if(CollectionUtils.isEmpty(matchers)){
			return false ; 
		}
		for(AuthExcludeMatcher matcher:matchers){
			if(matcher.match(request, handler)){
				return true ; 
			}
		}
		return false;
	}

	public void add(AuthExcludeMatcher authExcludeMatcher) {
		if(this.matchers == null){
			this.matchers = new ArrayList<AuthExcludeMatcher>() ; 
		}
		this.matchers.add(authExcludeMatcher) ; 
	}

}
