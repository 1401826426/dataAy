package com.fei.common.log;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.fei.common.redis.Channel;
import com.fei.common.rpc.framework.converter.Converter;
import com.fei.common.rpc.framework.converter.ConverterException;
import com.fei.common.zookeeper.AbstractZookeeperServerCenter;
import com.fei.common.zookeeper.server.Server;

import redis.clients.jedis.Jedis;

public class RedisAppender extends AppenderSkeleton{
	
	private JedisManager jedisManager  ; 
	
	private Converter converter ; 
	
	private AbstractZookeeperServerCenter zookeeperServerCenter ; 
	
	@Override
	public void close() {
		
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		Jedis jedis = jedisManager.getJedis() ;
		try {
			Server server = zookeeperServerCenter.getSelfServer() ; 
			LogObject logObject = new LogObject(server,event) ;
			byte[] bytes = converter.writeValue(logObject)  ;
			String message = new String(bytes,"utf-8") ;
			jedis.publish(Channel.LOG.getChannel(), message);
		} catch (ConverterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}

}
