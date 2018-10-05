package com.fei.common.jedis;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.fei.common.redis.Channel;
import com.fei.common.redis.IJedisPubSub;
import com.fei.common.redis.JedisClientManager;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class JedisClientManagerTest {
	
	
	@Test
	public void jedisTest() throws InterruptedException{
		JedisPool jedisPool = new JedisPool("127.0.0.1",6379) ; 
		JedisClientManager jedisClientManager = new JedisClientManager(jedisPool) ;
		doTestPubSub(jedisClientManager,"testJedis") ;  
	}
	
	public void jedisClusterTest() throws InterruptedException{
		Set<HostAndPort> set = new HashSet<>() ; 
		set.add(new HostAndPort("127.0.0.1",7000));
		set.add(new HostAndPort("127.0.0.1",7001));
		set.add(new HostAndPort("127.0.0.1",7002));
		JedisCluster jedisCluster = new JedisCluster(set) ; 
		JedisClientManager jedisClientManager = new JedisClientManager(jedisCluster) ; 
		doTestPubSub(jedisClientManager,"testCluster") ; 
	}
	
	private void doTestPubSub(JedisClientManager jedisClientManager,String name) throws InterruptedException {
//		jedisClientManager.setInterface(IJedisPubSub.class);
		IJedisPubSub jedisPubSub = jedisClientManager.getInterface(IJedisPubSub.class) ; 
		CountDownLatch countDownLatch = new CountDownLatch(1) ;
		Executors.newSingleThreadExecutor().execute(new Runnable(){

			@Override
			public void run() {
				jedisPubSub.subscribe(new JedisPubSub() {

					@Override
					public void onMessage(String channel, String message) {
						System.out.println("name="+name+",message--------->"+"channel="+channel+",message="+message);
					}

					@Override
					public void onSubscribe(String channel, int subscribedChannels) {
						System.out.println("name="+name+",subscribe,channle="+channel);
						countDownLatch.countDown(); 
					}
					
				}, Channel.LOG.getChannel());
			}
			
		});
		countDownLatch.await();  
		jedisPubSub.publish(Channel.LOG.getChannel(),name) ;
	}

	public static void main(String[] args) throws InterruptedException {
		new JedisClientManagerTest().jedisTest();  
		new JedisClientManagerTest().jedisClusterTest();
	}
}
