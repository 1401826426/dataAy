package com.fei.common.jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class JedisTests {
	
	private Jedis jedis; 
	
	@Before
	public void before(){
		jedis = new Jedis("127.0.0.1",6379) ; 
	}
	
	
	@Test
	public void testString(){
		System.out.println("=========================testString==============================");
		jedis.set("testStrKey1", "testStrValue1") ;
		//设置指定 key的值
		jedis.set("testStrKey2","testStrValue2") ;
		jedis.set("testNumber","0") ; 
		//获取指定 key的值
		System.out.println("get="+jedis.get("testStrKey1")) ; 
		//返回 key 中字符串值的子字符
		System.out.println("getRange="+jedis.getrange("testStrKey1", 1, 10)) ;
		//将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
		System.out.println("getSet="+jedis.getSet("testStrKey1", "testStrValue2"));
		//获取所有(一个或多个)给定 key 的值。
		System.out.println("mget="+jedis.mget(new String[]{"testStrKey2","testStrKey2"}));
		//将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。
		System.out.println("setex="+jedis.setex("testStrKey1", 10, "testStrValueEx")) ; 
		//用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。
		System.out.println("setRange="+jedis.setrange("testStrKey1",10,"setRange"));
		//返回 key 所储存的字符串值的长度。
		System.out.println("strLen="+jedis.strlen("testStrKey1"));
		//同时设置一个或多个 key-value 对。
		System.out.println("mset="+jedis.mset("msetKey1","msetValue1","msetKey2","msetValue2"));
		//同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
		System.out.println("msetnx="+jedis.msetnx("msetKeyNx1","msetNxValue1","msetNxKey2","msetNxValue2"));
		//将 key 中储存的数字值增一。
		System.out.println("incr="+jedis.incr("testKeyNum"));
		//将 key 所储存的值加上给定的增量值（increment） 。
		System.out.println("incrby="+jedis.incrBy("testNumber",3));
		//将 key 所储存的值加上给定的浮点增量值（increment） 
		System.out.println("incrbyfloat="+jedis.incrByFloat("testNumber",4.0));
		//将 key 中储存的数字值减一。
		System.out.println("decr="+jedis.decr("testNumber"));
		//key 所储存的值减去给定的减量值（decrement） 。
		System.out.println("decrBy="+jedis.decrBy("testNumber",1));
		//如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。
		System.out.println("append="+jedis.append("testStrKey1", "append"));
		Set<String> keys = jedis.keys("*") ;
		for(String key:keys){
			System.out.println(key+"="+jedis.get(key));
			jedis.del(key) ; 
		}
	}
	
	
	//对于hash的一个应用场景为缓存表,以表为key
	//另外一种场景为top排名
	@Test
	public void testHash(){ 
		//将哈希表 key 中的字段 field 的值设为 value 。
		System.out.println("hset="+jedis.hset("key1", "field1", "value1"));
		//只有在字段 field 不存在时，设置哈希表字段的值。
		System.out.println("hsetnx="+jedis.hsetnx("key1", "field1", "value2"));
		//获取存储在哈希表中指定字段的值。
		System.out.println("hget="+jedis.hget("key1","field1"));
		Map<String,String> map = new HashMap<>() ; 
		map.put("field2", "value2") ;
		map.put("field3", "value3") ;
		map.put("field4", "value4") ;
		//同时将多个 field-value (域-值)对设置到哈希表 key 中
		System.out.println("hmset="+jedis.hmset("key2", map));
		//获取所有给定字段的值
		System.out.println("hmget="+jedis.hmget("key2","field2","field3"));
		//获取哈希表中所有值
		System.out.println("hvals="+jedis.hvals("key2"));
		//获取在哈希表中指定 key 的所有字段和值
		System.out.println("hgetall="+jedis.hgetAll("key2"));
		//获取哈希表中字段的数量
		System.out.println("hlen="+jedis.hlen("key2"));
		//获取所有哈希表中的字段
		System.out.println("hkeys="+jedis.hkeys("key2"));
		//查看哈希表 key 中，指定的字段是否存在。
		System.out.println("hexists="+jedis.hexists("key2","field2"));
		System.out.println("hexists="+jedis.hexists("key2","field5"));
		//为哈希表 key 中的指定字段的整数值加上增量 increment 。
		System.out.println("hincr="+jedis.hincrBy("key1","field2", 10));
		//为哈希表 key 中的指定字段的浮点数值加上增量 increment 。
		System.out.println("hincrfloat="+jedis.hincrByFloat("key1","field2", 10.00));
		//迭代哈希表中的键值对。
		System.out.println("hscan="+jedis.hscan("key2", "1"));
		//删除一个或多个哈希表字段
		System.out.println("hdel="+jedis.hdel("key1","field1","field2"));
		//删除一个或多个哈希表字段
		System.out.println("del="+jedis.del("key2"));	
	}
	
	@Test
	public void testList(){
		Object lock = new Object() ; 
		System.out.println("======================================testList========================");
		//将一个值插入到已存在的列表头部
		System.out.println("lpushx="+jedis.lpushx("listKey1", "val4")) ; 
		//将一个或多个值插入到列表头部
		System.out.println("lpush="+jedis.lpush("listKey1", "val1","val2","val3")) ;
		//在列表中添加一个或多个值
		System.out.println("rpush="+jedis.rpush("listKey1", "rval1","rval2")) ; 
		//为已存在的列表添加值
		System.out.println("rpushx="+jedis.rpush("listKey1", "rval3"));
		//在列表的元素前或者后插入元素
		System.out.println("linsert="+jedis.linsert("listKey1",LIST_POSITION.BEFORE,"val3","valBefore"));
		System.out.println("linsert="+jedis.linsert("listKey1",LIST_POSITION.AFTER,"val1","valAfter"));
		//获取列表指定范围内的元素
	    System.out.println("lrange="+jedis.lrange("listKey1", 0,10));
	    //获取列表长度
	    System.out.println("len="+jedis.llen("listKey1"));
	    //通过索引获取列表中的元素
	    System.out.println("lindex="+jedis.lindex("listKey1", 0));
	    //通过索引设置列表元素的值
	    System.out.println("lset="+jedis.lset("listKey1", 0, "setVal")) ; 
		//移出并获取列表的第一个元素
		System.out.println("lpop="+jedis.lpop("listKey1")) ; 
		//移除并获取列表最后一个元素
		System.out.println("rpop="+jedis.rpop("listKey1")) ; 
		//对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
		System.out.println("ltrim="+jedis.ltrim("listKey1", 0, 5)) ; 
		//移除列表元素
		System.out.println("lrem="+jedis.lrem("listKey1", 1, "val2"));
		//移除列表的最后一个元素，并将该元素添加到另一个列表并返回
		System.out.println("rpoplpush="+jedis.rpoplpush("listKey1", "listKey2"));
		
		Executors.newScheduledThreadPool(1).schedule(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				synchronized(lock){
					Jedis jedis = new Jedis("127.0.0.1",6379) ; 
					System.out.println(jedis.lpush("listKey3", "val3","val4","val5")) ; 
					System.out.println(jedis.lpush("listKey4", "4val1","4val2","4val3")) ;
					jedis.close(); 
					return null;
				}
			}
			
		}, 5, TimeUnit.SECONDS) ; 
		//从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
		System.out.println("brpoplpush="+jedis.brpoplpush("listKey3","listKey4",10));
		synchronized (lock) {
			System.out.println("lrange1="+jedis.lrange("listKey1", 0, 10)) ; 
			System.out.println("lrange2="+jedis.lrange("listKey2", 0, 10)) ;
			System.out.println("lrange3="+jedis.lrange("listKey3", 0, 10)) ;
			System.out.println("lrange4="+jedis.lrange("listKey4", 0, 10)) ;
			jedis.del("listKey1") ; 
			jedis.del("listKey2") ; 
			jedis.del("listKey3") ; 
			jedis.del("listKey4") ; 
		}
		
	}
	
	@Test
	public void testSet(){
		System.out.println("============================================testSet============================");
		//向集合添加一个或多个成员
		System.out.println("sadd="+jedis.sadd("setKey1", "val1","val2","val3")) ;
		System.out.println("sadd="+jedis.sadd("setKey2", "val1","val2","val4")) ;
		//获取集合的成员数
		System.out.println("scard="+jedis.scard("setKey1")) ; 
		//返回给定所有集合的差集
		System.out.println("sdiff="+jedis.sdiff("setKey1","setKey2")) ;
		//返回给定所有集合的差集并存储在 destination 中
		System.out.println("sdiffstore="+jedis.sdiffstore("setKey3", "setKey1","setKey2"));
		//返回给定所有集合的交集
		System.out.println("sinter="+jedis.sinter("setKey1","setKey2"));
		//返回给定所有集合的交集并存储在 destination 中
		System.out.println("sinterstore="+jedis.sinterstore("setKey4", "setKey1","setKey2"));
		//判断 member 元素是否是集合 key 的成员
		System.out.println("sismember="+jedis.sismember("setKey1","val1"));
		//将 member 元素从 source 集合移动到 destination 集合
		System.out.println("smove="+jedis.smove("setKey1", "setKey3", "val1"));
		//移除并返回集合中的一个随机元素
		System.out.println("spop="+jedis.spop("setKey2"));
		//返回集合中一个或多个随机数
		System.out.println("srandmember="+jedis.srandmember("setKey2",1));
		//移除集合中一个或多个成员
		System.out.println("srem="+jedis.srem("setKey1", "val2"));
		//返回所有给定集合的并集
		System.out.println("sunion="+jedis.sunion("setKey1","setKey2","setKey3","setKey4"));
		//所有给定集合的并集存储在 destination 集合中
		System.out.println("sunionstore="+jedis.sunionstore("setKey5","setKey1","setKey2"));
		//返回集合中的所有成员
		System.out.println("smembers1="+jedis.smembers("setKey1"));
		System.out.println("smembers2="+jedis.smembers("setKey2"));
		System.out.println("smembers3="+jedis.smembers("setKey3"));
		System.out.println("smembers4="+jedis.smembers("setKey4"));
		System.out.println("smembers5="+jedis.smembers("setKey5"));
		jedis.del("setKey1") ; 
		jedis.del("setKey2") ; 
		jedis.del("setKey3") ; 
		jedis.del("setKey4") ; 
		jedis.del("setKey5") ; 
	}
		
	@Test
	public void testSortedSet(){
		System.out.println("==============================testSortedSet================================");
		Map<String,Double> map = new HashMap<String,Double>() ; 
		map.put("val1", 1.0) ; 
		map.put("val2", 2.0) ;
		map.put("val3", 3.0) ;
		Map<String,Double> map2 = new HashMap<String,Double>() ; 
		map2.put("val3", 1.0) ;
		map2.put("val4", 2.0) ;
		map2.put("val5", 3.0) ;
		//向有序集合添加一个或多个成员，或者更新已存在成员的分数
		System.out.println("zadd="+jedis.zadd("sortedSetKey1", map));
		System.out.println("zadd="+jedis.zadd("sortedSetKey2", map2));
		//获取有序集合的成员数
		System.out.println("zcard="+jedis.zcard("sortedSetKey1"));
		//计算在有序集合中指定区间分数的成员数
		System.out.println("zcount="+jedis.zcount("sortedSetKey1", 1.0, 2.0));
		//有序集合中对指定成员的分数加上增量 increment
		System.out.println("zincrby="+jedis.zincrby("sortedSetKey1", 2.0, "val2"));
		//计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
		System.out.println("zinterstore="+jedis.zinterstore("sortedSetKey3","sortedSetKey1","sortedSetKey2"));
		//在有序集合中计算指定字典区间内成员数量
		System.out.println("zlxcount="+jedis.zlexcount("sortedSetKey1", "[val1", "[val5"));
		//通过索引区间返回有序集合成指定区间内的成员
		System.out.println("zrange="+jedis.zrange("sortedSetKey1",0, 3));
		//通过字典区间返回有序集合的成员
		System.out.println("zrangebylex="+jedis.zrangeByLex("sortedSetKey1", "[val1", "[val2"));
		//通过分数返回有序集合指定区间内的成员
		System.out.println("zrangebyscore="+jedis.zrangeByScore("sortedSetKey1",1.0, 5.0));
		//返回有序集合中指定成员的索引
		System.out.println("zrank="+jedis.zrank("sortedSetKey1","val1"));
		//返回有序集中指定区间内的成员，通过索引，分数从高到底
		System.out.println("zrevrange="+jedis.zrevrange("sortedSetKey1", 0, 10));
		//返回有序集中指定分数区间内的成员，分数从高到低排序
		System.out.println("zrevrangebyscore="+jedis.zrevrangeByScore("sortedSetKey1",3.0, 1.0));
		//返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
		System.out.println("zrevrank="+jedis.zrevrank("sortedSetKey1", "val2"));
		//返回有序集中，成员的分数值
		System.out.println("zcore="+jedis.zscore("sortedSetKey3", "val3"));
		//计算给定的一个或多个有序集的并集，并存储在新的 key 中,对于相同的元素,其score相加
		System.out.println("zunionstore="+jedis.zunionstore("sortedSetKey4", "sortedSetKey1","sortedSetKey2","sortedSetKey3"));
		System.out.println("zrange="+jedis.zrange("sortedSetKey4",0,10)) ;
		System.out.println("zcore="+jedis.zscore("sortedSetKey4", "val3"));
		//移除有序集合中的一个或多个成员
		System.out.println("zrem="+jedis.zrem("sortedSetKey1", "val1","val2"));
		//移除有序集合中给定的字典区间的所有成员
		System.out.println("zremrangebylex="+jedis.zremrangeByLex("sortedSetKey2", "[val3", "[val5"));
		//移除有序集合中给定的排名区间的所有成员
		System.out.println("zremrangebyrank="+jedis.zremrangeByRank("sortedSetKey1", 0, 2));
		//移除有序集合中给定的分数区间的所有成员
		System.out.println("zremrangebyscore="+jedis.zremrangeByScore("sortedSetKey4", 1.0, 3.0));
		jedis.del("sortedSetKey1") ; 
		jedis.del("sortedSetKey2") ; 
		jedis.del("sortedSetKey3") ; 
		jedis.del("sortedSetKey4") ; 
	}
	
	
	@Test
	public void testPublish() throws InterruptedException{
		System.out.println("============================testpublish=======================");
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				Jedis jedis = new Jedis("127.0.0.1",6379) ; 
				jedis.psubscribe(new MyJedisPubSub("testPub*"), "testPub*");
				jedis.close();
			}
		});
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				Jedis jedis = new Jedis("127.0.0.1",6379) ; 
				jedis.subscribe(new MyJedisPubSub("testPub1"), "testPub1");
				jedis.close();
			}
		});
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				Jedis jedis = new Jedis("127.0.0.1",6379) ; 
				jedis.subscribe(new MyJedisPubSub("testPub2"), "testPub2");
				jedis.close(); 
			}
		});
		Thread.sleep(1000);
		jedis.publish("testPub1", "hahaha1") ; 
		jedis.publish("testPub2", "hahaha2") ; 
		Thread.sleep(100000);
	}
	
	@After
	public void after(){
		jedis.close();  
	}
	
	private static class MyJedisPubSub extends JedisPubSub{
		
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
	
}
















