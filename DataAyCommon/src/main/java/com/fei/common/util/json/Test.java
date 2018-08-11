package com.fei.common.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"name\":\"Test1\",\"annos\":[{\"name\":\"HtmlField\",\"attrs\":{\"cssPath\":\"afqrq\"}},{\"name\":\"Image\",\"attrs\":{\"value\":\"src\",\"download\":\"ert\"}}],\"child\":[{\"name\":\"child1\",\"annos\":[{\"name\":\"Image\",\"attrs\":{\"value\":\"src\",\"download\":\"qwe\"}}]}]}" ; 
		ObjectMapper mapper = new ObjectMapper() ; 
		Node node = mapper.readValue(json, Node.class) ;
		String newJson = node.toString() ;
		String nnJson = newJson.replaceAll("(\\w+)","\""
				+ "\\1\"") ; 
		System.err.println(node);		
		System.err.println(nnJson);
	}
	
}
