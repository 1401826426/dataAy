package com.fei.crawlers;

import java.util.List;
import java.util.Map;

import com.geccocrawler.gecco.spider.SpiderBean;

public class MySpiderBean implements SpiderBean{

	private static final long serialVersionUID = 1L;
	
	private List<Node> nodes; 
	
	private Map<String,FieldValue> values ;

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public Map<String, FieldValue> getValues() {
		return values;
	}

	public void setValues(Map<String, FieldValue> values) {
		this.values = values;
	} 
	
	
	
}
