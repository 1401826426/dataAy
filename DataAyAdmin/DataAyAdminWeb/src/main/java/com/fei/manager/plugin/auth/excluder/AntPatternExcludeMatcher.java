package com.fei.manager.plugin.auth.excluder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

public class AntPatternExcludeMatcher implements AuthExcludeMatcher{
	
	private UrlPathHelper urlPathHelper ; 
	
	private PathMatcher pathMatcher ; 
	
	private Set<String> patterns ; 
	
	private Set<String> matchedUrl  ; 
	
	public AntPatternExcludeMatcher(UrlPathHelper urlPathHelper,PathMatcher pathMatcher){
		this.urlPathHelper = urlPathHelper == null ? new UrlPathHelper() : urlPathHelper ; 
		this.pathMatcher = pathMatcher == null ? new AntPathMatcher() : pathMatcher ;
		this.matchedUrl = new HashSet<String>() ; 
		this.patterns = new HashSet<String>() ; 
	}
	
	
	
	public AntPatternExcludeMatcher(UrlPathHelper urlPathHelper) {
		this(urlPathHelper,null) ; 
	}

	public AntPatternExcludeMatcher(PathMatcher pathMatcher) {
		this(null,pathMatcher) ; 
	}

	public AntPatternExcludeMatcher() {
		this(null,null) ; 
	}
	
	public void setPatterns(Collection<String> patterns){
		this.patterns.addAll(patterns) ; 
 	}
	
	public void setPattern(String pattern){
//		StringT
		this.patterns.add(pattern) ; 
	}
	
	@Override
	public boolean match(HttpServletRequest request, Object handler) {
		String url = this.urlPathHelper.getLookupPathForRequest(request) ; 
		if(this.matchedUrl.contains(url)){
			return true ; 
		}
		for(String pattern:patterns){
			if(this.pathMatcher.match(pattern,url)){
				this.matchedUrl.add(url) ; 
				return true; 
			}
		}
		return false;
	}

}
