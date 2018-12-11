package com.fei.common.cache;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

import com.fei.common.redis.IJedisHashCommand;
import com.fei.netty.springmvc.converter.Converter;

public class SpringJedisCacheContextRegister implements InitializingBean,BeanFactoryAware{
	
	private BeanFactory beanFactory ; 

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory  ; 
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		IJedisHashCommand jedisHashCommand = beanFactory.getBean(IJedisHashCommand.class) ;
		JedisCacheContext.getInstance().setJedisHashCommand(jedisHashCommand);
		try{
			Converter converter = beanFactory.getBean(Converter.class) ;
			JedisCacheContext.getInstance().setConverter(converter);
		}catch(Exception e){
			//no op
		}
	}

}
