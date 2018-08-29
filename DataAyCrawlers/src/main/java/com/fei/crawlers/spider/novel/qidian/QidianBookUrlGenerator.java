package com.fei.crawlers.spider.novel.qidian;

import java.util.concurrent.atomic.AtomicInteger;

public class QidianBookUrlGenerator {
	
	private static String baneUrl = "https://www.qidian.com/all?page=" ;
	
	private static AtomicInteger page = new AtomicInteger(1) ; 
	
	public static String generateBookDownLoadUrl(int bookId){
		return "http://download.qidian.com/epub/"+bookId+".epub" ; 
	}
	
	public static String next(){
		return baneUrl + page.addAndGet(1) ; 
	}
	
	
}
