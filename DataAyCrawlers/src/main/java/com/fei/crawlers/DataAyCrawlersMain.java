package com.fei.crawlers;

import com.fei.netty.springmvc.NettyBootstrapV2;

public class DataAyCrawlersMain {

	public static void main(String[] args) {
		NettyBootstrapV2 bootstrap = new NettyBootstrapV2("classpath:conf.xml");
		bootstrap.start();
	}

}
