package com.fei.common.log;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

import com.fei.common.redis.IJedisPubSub;
import com.fei.netty.springmvc.converter.Converter;
import com.fei.netty.springmvc.zookeeper.AbstractZookeeperServerCenter;


public class SpringLogContextRegister implements InitializingBean , BeanFactoryAware{
	
	private BeanFactory beanFactory ; 
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory; 
		
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		AbstractZookeeperServerCenter serverCenter = beanFactory.getBean(AbstractZookeeperServerCenter.class) ; 
		if(serverCenter != null){
			JedisLogContext.getInstance().registerSelfServer(serverCenter.getSelfServer()); 
		}
		IJedisPubSub jedisPubSub = beanFactory.getBean(IJedisPubSub.class) ;  
		JedisLogContext.getInstance().registerJedisPubSub(jedisPubSub);
		try{
			Converter converter = beanFactory.getBean(Converter.class) ; 
			if(converter != null){
				JedisLogContext.getInstance().registerConverter(converter) ; 
			}
		}catch(Exception e){
			
		}
		
	}

}
