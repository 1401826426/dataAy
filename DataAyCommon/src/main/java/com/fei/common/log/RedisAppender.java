package com.fei.common.log;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.fei.common.redis.Channel;
import com.fei.common.redis.IJedisPubSub;
import com.fei.netty.springmvc.converter.ConverterException;
import com.fei.netty.springmvc.zookeeper.server.Server;

public class RedisAppender extends AppenderSkeleton{ 
	
	@Override
	public void close() {
		
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent event) {
		IJedisPubSub jedisPubSub = JedisLogContext.getInstance().getJedisPubSub() ;
		try {
			Server server = JedisLogContext.getInstance().getSelfServer() ;  
			LogObject logObject = new LogObject(server,event) ;
			byte[] bytes = JedisLogContext.getInstance().getConverter().writeValue(logObject)  ;
			String message = new String(bytes,"utf-8") ;
			jedisPubSub.publish(Channel.LOG.getChannel(), message);
		} catch (ConverterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}

}
