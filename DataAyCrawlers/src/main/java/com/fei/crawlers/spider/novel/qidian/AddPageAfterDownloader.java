package com.fei.crawlers.spider.novel.qidian;

import com.geccocrawler.gecco.annotation.GeccoClass;
import com.geccocrawler.gecco.downloader.AfterDownload;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;

@GeccoClass(value={BookList.class})
public class AddPageAfterDownloader implements AfterDownload{

	@Override
	public void process(HttpRequest request, HttpResponse response) {
		HttpRequest subRequest = request.subRequest(QidianBookUrlGenerator.next()) ;
		DeriveSchedulerContext.into(subRequest) ; 
	}
	

}
