package com.fei.common.log;

import org.apache.log4j.spi.LoggingEvent;

import com.fei.common.zookeeper.server.Server;

public class LogObject {
	
	private Server server ; 
	
	private LoggingEvent LoggingEvent ;

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public LoggingEvent getLoggingEvent() {
		return LoggingEvent;
	}

	public void setLoggingEvent(LoggingEvent LoggingEvent) {
		this.LoggingEvent = LoggingEvent;
	}

	public LogObject(Server server, LoggingEvent LoggingEvent) {
		super();
		this.server = server;
		this.LoggingEvent = LoggingEvent;
	} 
	
	
	
}
