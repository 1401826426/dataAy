package com.fei.crawlers.spider.baidu.wenku;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.spider.HtmlBean;

public class OnePagePpt implements HtmlBean{
	
	private static final long serialVersionUID = 1L;
	
	@Image
    @HtmlField(cssPath=".ppt-image-wrap img")
	private String pic ;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	} 
	
	
}
