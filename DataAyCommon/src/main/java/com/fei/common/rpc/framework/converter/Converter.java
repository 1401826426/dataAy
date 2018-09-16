package com.fei.common.rpc.framework.converter;

import java.lang.reflect.Type;

public interface Converter {
	
	public Object readValue(byte[] bytes,Type type) throws ConverterException ; 
	
	public byte[] writeValue(Object value) throws ConverterException; 
	
}
