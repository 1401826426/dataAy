package com.fei.common.util.json;

import java.util.List;

public class Node {
	
	private String name ; 
	
	private List<Anno> annos ; 
	
	private List<Node> child ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Anno> getAnnos() {
		return annos;
	}

	public void setAnnos(List<Anno> annos) {
		this.annos = annos;
	}

	public List<Node> getChild() {
		return child;
	}

	public void setChild(List<Node> child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "{name:" + name + ", annos:" + annos + ", child:" + child + "}";
	} 
	
	
}
