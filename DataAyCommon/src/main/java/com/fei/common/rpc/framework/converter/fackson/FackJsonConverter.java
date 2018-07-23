package com.fei.common.rpc.framework.converter.fackson;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fei.common.rpc.framework.converter.Converter;
import com.fei.common.rpc.framework.converter.ConverterException;

public class FackJsonConverter implements Converter{
	
	private ObjectMapper mapper = new ObjectMapper(); 
	
	
	public Object readValue(byte[] bytes,Type type) throws ConverterException{
		try{
			TypeReference<Object> typeReference = new TypeReference<Object>() {
				@Override
				public Type getType() {
					return type ;  
				}
			};
			return mapper.readValue(bytes,typeReference);
		}catch (Exception e) {
			throw new ConverterException(e) ; 
		}
		 
	}
	
	public byte[] writeValue(Object value) throws ConverterException{
		try{
			return mapper.writeValueAsBytes(value) ; 
		}catch(Exception e){
			throw new ConverterException(e) ; 
		}
	}
	
}