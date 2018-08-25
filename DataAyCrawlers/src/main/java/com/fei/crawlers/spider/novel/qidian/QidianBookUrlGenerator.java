package com.fei.crawlers.spider.novel.qidian;

import java.util.concurrent.atomic.AtomicInteger;

public class QidianBookUrlGenerator {
	
	private static String baneUrl = "https://www.qidian.com/all?page=" ;
	
	private static AtomicInteger page = new AtomicInteger(0) ; 
	
	
	public static String next(){
		return baneUrl + page.addAndGet(1) ; 
	}
	
	
}
