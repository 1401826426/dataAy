package com.fei.admin.plugin.auth.module;

public enum ModuleEnum {
	
	AUTH(1) , 
	
	CRAWLER(2) , 
	
	NOTE(3) , 
	
	ANALYZE(4) , 
	
	TOOL(5) ,
	
	;
	
	private int moduleId ; 
	
	private ModuleEnum(int moduleId){
		this.moduleId = moduleId ; 
	}

	public int getModuleId(){
		return moduleId ; 
	}
	
}








