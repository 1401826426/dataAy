package com.fei.crawlers.spider.novel.qidian;

import java.util.List;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="https://www.qidian.com/all?page={page}", pipelines="consolePipeline")
public class BookList implements HtmlBean{

	private static final long serialVersionUID = 1L;
	
	@Href(click=true)
	@HtmlField(cssPath=".all-book-list .book-img-text .all-img-list li .book-mid-info h4 a")
	private List<String> herfs ;

	public List<String> getHerfs() {
		return herfs;
	}

	public void setHerfs(List<String> herfs) {
		this.herfs = herfs;
	}
	
	public static void main(String[] args) {
		GeccoEngine.create()
        .classpath("com.fei.crawlers.novel.qidian")
        .start(QidianBookUrlGenerator.next())
        .thread(1)
        .interval(2000)
        .debug(true)
        .loop(false)
        .mobile(false)
        .start();
	}
	
}











