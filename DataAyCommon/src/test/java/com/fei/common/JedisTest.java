package com.fei.common;

import java.util.List;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379) ;
		jedis.del("t_1") ;
		jedis.lpush("t_l","1") ; 
		jedis.lpush("t_1","2") ; 
		jedis.lpush("t_1","3") ;
		List<String> list = jedis.lrange("t_1",0,2) ; 
		for(String str:list){
			System.out.println(str);
		}
		//set get test
		jedis.set("test", "test1");
		System.out.println(jedis.get("test"));
		jedis.set("test", "test2");
		System.out.println(jedis.get("test"));
		
		//hash test
		jedis.hsetnx("ttt", "ttt1", "ksduew") ; 
		System.out.println(jedis.hget("ttt", "ttt1")) ; 
		
//		Set<HostAndPort> nodes = new HashSet<>(); 
//		JedisCluster jedisCluser = new JedisCluster(nodes) ;
//		jedisCluser.g
		jedis.close();
		
	}
	
}
