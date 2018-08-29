package com.fei.crawlers.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.ConsolePipeline;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.spider.SpiderBean;

import util.clazz.ClazzUtil;
import util.collection.CollectionUtils;

@Component
public class MyPipelineFactory implements PipelineFactory,InitializingBean{
	
	private static Logger logger = LoggerFactory.getLogger(MyPipelineFactory.class) ; 
	
	@Autowired
	private List<Pipeline<? extends SpiderBean>> pipelines ;
	
	private Map<String,Pipeline<? extends SpiderBean>> map  ;
	
	public List<Pipeline<? extends SpiderBean>> getPipelines() {
		return pipelines;
	}

	public void setPipelines(List<Pipeline<? extends SpiderBean>> pipelines) {
		this.pipelines = pipelines;
	}

	@Override
	public Pipeline<? extends SpiderBean> getPipeline(String name) {
		return map.get(name);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		map = new HashMap<>() ; 
		if(!CollectionUtils.isEmpty(pipelines)){
			for(Pipeline<? extends SpiderBean> pipeline:pipelines){
				PipelineName pipelineName = ClazzUtil.getAnnotation(pipeline.getClass(),PipelineName.class) ;
				if(pipelineName == null){
					logger.warn("没有PipelineName的pipeLine:"+pipeline.getClass());
				}
				String name = pipelineName.value() ; 
				map.put(name, pipeline) ; 
			}
		}
		if(map.containsKey("consolePipeline")){
			map.put("consolePipeline",new ConsolePipeline()) ;
		}
	}

}