package com.fei.crawlers.framework;

import java.io.IOException;

import org.springframework.core.io.Resource;

import com.geccocrawler.gecco.pipeline.PipelineFactory;

import util.data.DataParser;
import util.data.DataParserBuilder;

public class GeccoStarter{ 
	
	@SuppressWarnings("unused")
	private Configuration conf ;

	private PipelineFactory pipelineFactory ; 

	public GeccoStarter(Resource resource) throws IOException{
		DataParser dataParser = DataParserBuilder.getInstance().getXmlDataParser() ;
		this.conf = dataParser.parse(Configuration.class, resource.getInputStream()) ; 
	}	
	
	public PipelineFactory getPipelineFactory() {
		return pipelineFactory;
	}



	public void setPipelineFactory(PipelineFactory pipelineFactory) {
		this.pipelineFactory = pipelineFactory;
	}



	public void start() throws Exception { 
//		for(GeccoConf geccoConf:conf.getConfs()){
//			GeccoEngine.create(geccoConf.getClassPath(),pipelineFactory)
//			           .start(geccoConf.getStartUrl())
//			           .interval(geccoConf.getInterval())
//			           .debug(false)
//			           .start();  
//		}
	}
}
