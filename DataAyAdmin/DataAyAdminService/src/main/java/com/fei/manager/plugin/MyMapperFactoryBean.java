package com.fei.manager.plugin;

import org.mybatis.spring.mapper.MapperFactoryBean;

public class MyMapperFactoryBean<T> extends MapperFactoryBean<T> {

	@Override
	public T getObject() throws Exception {
		T object = super.getObject();
		object.getClass() ; 
		
		return object;
	}

}
