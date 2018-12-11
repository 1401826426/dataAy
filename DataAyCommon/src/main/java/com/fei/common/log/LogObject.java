package com.fei.common.log;

import org.apache.log4j.spi.LoggingEvent;

import com.fei.netty.springmvc.zookeeper.server.Server;

public class LogObject {
	
	private Server server ; 
	
	private LogEvent LogEvent ;

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	
	
	
	public LogObject() {
		super();
	}

	public LogEvent getLogEvent() {
		return LogEvent;
	}

	public void setLogEvent(LogEvent logEvent) {
		LogEvent = logEvent;
	}

	public LogObject(Server server, LoggingEvent LoggingEvent) {
		super();
		this.server = server;
		this.LogEvent = new LogEvent(LoggingEvent) ; 
	} 
	
	
	
}
