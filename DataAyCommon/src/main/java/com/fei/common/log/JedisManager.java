package com.fei.common.log;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@SuppressWarnings("unused")
public class JedisManager{
	
	private Jedis jedis ; 
	
	private JedisCluster jedisCluster ; 
	
	public void lpush(){
	}

	public Jedis getJedis() {
		return new Jedis("127.0.0.1",6379);
	}
	
}
