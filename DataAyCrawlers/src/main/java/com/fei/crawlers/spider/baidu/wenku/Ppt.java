package com.fei.crawlers.spider.baidu.wenku;

import java.util.List;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl = {"https://wenku.baidu.com/view/{id}.html"},pipelines={"baiduWenkuPptPipeline","consolePipeline"})
public class Ppt implements HtmlBean{ 
	
	private static final long serialVersionUID = -6905813283586331808L;

	@HtmlField(cssPath="html")
	private String context ; 
	
	@RequestParameter("id")
	private String id; 
	
	@HtmlField(cssPath=".flowPPt .reader_ab_test .doc-tittle-2")
	@Text
	private String name ; 

	
	@HtmlField(cssPath=".flowPPt .flow-ppt-wrap")
	private List<OnePagePpt> list ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<OnePagePpt> getList() {
		return list;
	}

	public void setList(List<OnePagePpt> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	public static void main(String[] args){
	       GeccoEngine.create()
	        .classpath("com.fei.crawlers.spider.baidu.wenku")
	        .start("https://wenku.baidu.com/view/3d6a01674431b90d6c85c7e2.html")
	        .thread(10)
	        .interval(2000)
	        .debug(true)
	        .loop(false)
	        .mobile(false)
	        .start();
	}
	
}
