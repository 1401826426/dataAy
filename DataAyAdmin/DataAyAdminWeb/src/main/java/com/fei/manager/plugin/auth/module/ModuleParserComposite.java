package com.fei.manager.plugin.auth.module;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ModuleParserComposite implements ModuleParser{
	
	private List<ModuleParser> parsers ;  

	@Override
	public ModuleEnum parseModule(HttpServletRequest request, Object handler) {
		if(this.parsers == null){
			return null ; 
		}
		for(ModuleParser parser:parsers){
			if(parser.support(handler)){
				return parser.parseModule(request, handler) ; 
			}
		}
		return null;
	}

	@Override
	public boolean support(Object handler) {
		if(this.parsers == null){
			return false ; 
		}
		for(ModuleParser parser:parsers){
			if(parser.support(handler)){
				return true ;  
			}
		}
		return false ; 
	}

	public void addParser(ModuleParser moduleParser) {
		if(this.parsers == null){
			this.parsers = new ArrayList<ModuleParser>() ; 
		}
		this.parsers.add(moduleParser) ; 
	}

}









