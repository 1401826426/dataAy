package com.fei.common.util.communication;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContent implements Content{
	
	protected List<String> tos; 

	public List<String> getTos() {
		return tos;
	}

	public void setTo(String to) {
		this.tos = new ArrayList<String>() ; 
		this.tos.add(to) ; 
	}
	
	public void setTos(List<String> tos) {
		assert tos != null ; 
		this.tos = tos ;  
	}
	
	public void addTo(String to){
		if(this.tos == null){
			this.tos = new ArrayList<String>() ; 
		}
		this.tos.add(to) ; 
	}
	
}
