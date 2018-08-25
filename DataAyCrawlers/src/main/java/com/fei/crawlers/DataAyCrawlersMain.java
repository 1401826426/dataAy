package com.fei.crawlers;

import com.fei.netty.springmvc.NettyBootstrap;

public class DataAyCrawlersMain {

	public static void main(String[] args) {
		NettyBootstrap bootstrap = new NettyBootstrap("classpath:conf.xml");
		bootstrap.start();
	}

}
