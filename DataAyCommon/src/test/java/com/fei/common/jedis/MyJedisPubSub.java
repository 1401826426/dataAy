package com.fei.common.jedis;

import redis.clients.jedis.JedisPubSub;

public class MyJedisPubSub extends JedisPubSub{
	
	private String name ; 
	
	public MyJedisPubSub(String name) {
		super();
		this.name = name;
	}

	//channel来消息
	@Override
	public void onMessage(String channel, String message) {
		System.out.println(name + ",onMessage,channel="+channel+",message="+message);
	}

	//pattern来消息
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println(name + ",onPMessage,pattern="+pattern+",channel="+channel+",message="+message);
	}

	//channel订阅
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println(name + ",onSubscribe,channel="+channel+",subscribedChannels="+subscribedChannels);
	}

	//channle解除订阅
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println(name + ",onUnsubscribe,channel="+channel+",subscribedChannels="+subscribedChannels);
	}

	//pattern解除订阅
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		System.out.println(name + ",onPUnsubscribe,pattern="+pattern+",subscribedChannels="+subscribedChannels);
	}

	//pattern订阅
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		System.out.println(name + ",onPSubscribe,pattern="+pattern+",subscribedChannels="+subscribedChannels);
	}

	
}
