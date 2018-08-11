package com.fei.crawlers;

import java.util.Map;

public class Anno {
	
	private String name; 
	
	private Map<String,String> attrs ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

	@Override
	public String toString() {
		return "{name:" + name + ",attrs:" + attrs + "}";
	}
	
	

	
	
	
}

