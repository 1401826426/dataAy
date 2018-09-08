package com.fei.common.converter;

import java.lang.reflect.Type;

public interface Converter {
	
	public Object readValue(byte[] bytes,Type type) throws ConverterException ; 
	
	public byte[] writeValue(Object value) throws ConverterException; 
	
	//将value的class一同写入byte中
	public byte[] writeValueWithType(Object value) throws ConverterException;
	
	//bytes中有value的class
	public Object readValueWithoutType(byte[] bytes) throws ConverterException;
	
}
