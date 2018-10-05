package com.fei.crawlers.spider.baidu.wenku;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.downloader.DownloadException;
import com.geccocrawler.gecco.downloader.DownloaderContext;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.geccocrawler.gecco.utils.DownloadImage;

@PipelineName("baiduWenkuPptPipeline")
@Controller
public class DownLoadPipeLine implements Pipeline<SpiderBean>{
	
	private String rootName = "F:\\images" ;
	
	@Override
	public void process(SpiderBean bean) {
		if(bean instanceof Ppt){
			Ppt ppt = (Ppt)bean ; 
			List<OnePagePpt> list = ppt.getList() ;
			int pos = 0 ; 
			for(OnePagePpt onePage:list){
				String url = onePage.getPic() ;
				String type = ".img"; 
				int index = url.lastIndexOf(".") ; 
				if(index != -1){
					type = url.substring(index) ; 
				}
				HttpRequest request = new HttpGetRequest(url) ; 
				try {
					HttpResponse subReponse = DownloaderContext.download(request) ;
					DownloadImage.download(rootName+"//"+ppt.getName(),String.valueOf(pos)+type,subReponse.getRaw());
				} catch (DownloadException e) {
					e.printStackTrace();
				} 
			}
		}
	}

}
