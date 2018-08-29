package com.fei.crawlers.framework;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;

@Component
public class GeccoStarter implements InitializingBean,ApplicationContextAware{
	
	@Autowired
	private PipelineFactory pipelineFactory ; 
	
	private Configuration conf ; 
	
	private ApplicationContext applicationContext ; 
	
	public PipelineFactory getPipelineFactory() {
		return pipelineFactory;
	}



	public void setPipelineFactory(PipelineFactory pipelineFactory) {
		this.pipelineFactory = pipelineFactory;
	}




	@Override
	public void afterPropertiesSet() throws Exception {
		Resource resource  = applicationContext.getResource("classpath:geccoConf.xml") ; 
		try {
			conf = Configuration.readFrom(resource.getInputStream()) ;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		for(GeccoConf geccoConf:conf.getConfs()){
			GeccoEngine.create(geccoConf.getClassPath(),pipelineFactory)
			           .start(geccoConf.getStartUrl())
			           .interval(geccoConf.getInterval())
			           .debug(false)
			           .start();  
		}
	}



	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext ; 
	}

}
