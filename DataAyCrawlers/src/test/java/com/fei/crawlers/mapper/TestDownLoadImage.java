package com.fei.crawlers.mapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class TestDownLoadImage {
	
	public static void main(String[] args) throws IOException{
		String[] strs = new String[]{
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hELAcUSDRZcKr_6b5Fesy40=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hH0p96MfJIpGDDU4VhJazK8=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hEwU9aTfIHkeYmJEtarK1Mg=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hGJhm3wh2xRo1GKxV4Kp5hU=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hKIFGe98GR72Fb_x0icHF30=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hDtpyst_MHUvo9qek2DjA60=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hFaVe8jnDyjo372DvUFmho8=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hJ@6BzWhW0e74c1WGaQvJu0=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hHsxHjmfNpY6P@oepJHedfA=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hES_oBNjC4LWAyHCT4LJdVg=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hOHYfwyhUVi7tDStOObfLCU=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hAxwblj@lKv76pC4t69@Qoc=",
				"https://view44.book118.com/img/?img=7o@o7xcocmm1TsngZevO5gqL5Y7dqHJw4hzLKJR2lul1ITsmUBa0hFBZZ8oUIjwyiGDTdUu2SL8="
		} ; 
		int pos = strs.length-1 ; 
		File parentFile = new File("F:\\images") ;
		for(String ss:strs){
			URL url = new URL(ss) ;
			InputStream is = url.openStream();
			File file = new File(parentFile,String.valueOf(pos--)+".jpg") ;
			if(!file.exists()){
				file.createNewFile() ; 
			}
			OutputStream os = new FileOutputStream(file) ;
			byte[] bytes = new byte[1024] ; 
			int len = -1 ; 
			while((len=is.read(bytes)) != -1){
				os.write(bytes,0, len);
			}
			os.close();  
		}
	}
	
}





