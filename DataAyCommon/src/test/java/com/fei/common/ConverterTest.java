package com.fei.common;

import com.fei.common.converter.Converter;
import com.fei.common.converter.ConverterException;
import com.fei.common.converter.fackson.FackJsonConverter;

public class ConverterTest {
	
	
	public static void main(String[] args) throws ConverterException {
		Converter converter = new FackJsonConverter() ; 
		Object a = new A(1,2) ;  
		byte[] bytes = converter.writeValueWithType(a) ; 
		System.out.println(converter.readValueWithoutType(bytes));
	}
	
	public static class A{
		int a ; 
		int b ;
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
		public A(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		public A() {
			super();
		}
		@Override
		public String toString() {
			return "A [a=" + a + ", b=" + b + "]";
		}
		
	}
	
	
	
}
