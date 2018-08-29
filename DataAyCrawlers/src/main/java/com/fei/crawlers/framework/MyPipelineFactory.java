package com.fei.crawlers.framework;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.ConsolePipeline;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.spider.SpiderBean;

public class MyPipelineFactory implements PipelineFactory,ApplicationContextAware{
	
	private ApplicationContext applicationContext ; 
	
	private Map<String,Pipeline<? extends SpiderBean>> map ; 
	
	@Override
	public Pipeline<? extends SpiderBean> getPipeline(String name) {
		if(map == null){
			synchronized (this) {
				if(map == null){					
					refreshMap(); 
				}
			}
		}
		return map.get(name);
	}

	@SuppressWarnings("unchecked")
	private void refreshMap() {
		map = new HashMap<>() ; 
		String[] beanNames = applicationContext.getBeanNamesForType(Pipeline.class) ; 
		if(beanNames != null){
			for(String name:beanNames){
				Pipeline<? extends SpiderBean> pipeline = (Pipeline<? extends SpiderBean>)applicationContext.getBean(name) ; 
				PipelineName pipelineName = pipeline.getClass().getAnnotation(PipelineName.class) ;
				String pipelineAnnoName = pipelineName.value() ; 
				map.put(pipelineAnnoName, pipeline) ; 
			}
		}
		if(!map.containsKey("consolePipeline")){
			map.put("consolePipeline",new ConsolePipeline()) ; 
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext ; 
	}


}

