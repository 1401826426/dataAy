package com.fei.admin;

import com.fei.netty.springmvc.NettyBootstrap;

public class DataAyAdminBootstrap {
	
	public static void main(String[] args) {
		NettyBootstrap bootstrap = new NettyBootstrap("classpath:conf.xml") ; 
		bootstrap.start();  
	}
	
}