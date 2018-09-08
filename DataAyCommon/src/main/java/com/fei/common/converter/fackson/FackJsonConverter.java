package com.fei.common.converter.fackson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fei.common.converter.Converter;
import com.fei.common.converter.ConverterException;

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

	@Override
	public byte[] writeValueWithType(Object val) throws ConverterException {
		byte[] valBytes = null;
		try {
			valBytes = writeValue(val);
		} catch (ConverterException e) {
			e.printStackTrace();
			return null ; 
		}
		Class<?> clazz = val.getClass() ; 
		String name = clazz.getName() ; 
		byte[] nameBytes = name.getBytes(Charset.forName("utf8")) ; 
		Integer len = nameBytes.length ;  
		ByteArrayOutputStream bos = new ByteArrayOutputStream() ; 
		bos.write(len);
		try {
			bos.write(nameBytes);
			bos.write(valBytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null ; 
		}
		return bos.toByteArray();
	}

	@Override
	public Object readValueWithoutType(byte[] bytes) throws ConverterException {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes) ;
		int len = bis.read() ; 
		byte[] nameBytes = new byte[len] ; 
		bis.read(nameBytes, 0, len) ; 
		String name = new String(nameBytes,Charset.forName("utf8")) ; 
		byte[] valBytes = new byte[bytes.length-1-len] ; 
		try {
			bis.read(valBytes) ;
			Class<?> clazz = Class.forName(name) ; 
			return readValue(valBytes, clazz) ; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ; 

	}
	
}
