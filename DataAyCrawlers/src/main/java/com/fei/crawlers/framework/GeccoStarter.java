package com.fei.crawlers.framework;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.geccocrawler.gecco.GeccoEngine;

public class GeccoStarter implements InitializingBean{
	
	private PipeLineFactory pipelineFactory ; 
	
	private Configuration conf ; 
	
	public GeccoStarter(Resource resource){
		try {
			conf = Configuration.readFrom(resource.getInputStream()) ;
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	public PipeLineFactory getPipelineFactory() {
		return pipelineFactory;
	}



	public void setPipelineFactory(PipeLineFactory pipelineFactory) {
		this.pipelineFactory = pipelineFactory;
	}




	@Override
	public void afterPropertiesSet() throws Exception {
		for(GeccoConf geccoConf:conf.getConfs()){
			GeccoEngine.create(geccoConf.getClassPath())
			           .start(geccoConf.getStartUrl())
			           .interval(geccoConf.getInterval())
			           .pipelineFactory(pipelineFactory)
			           .start();  
		}
	}

}
