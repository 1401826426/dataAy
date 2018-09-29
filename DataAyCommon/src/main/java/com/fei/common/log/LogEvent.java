package com.fei.common.log;

import org.apache.log4j.spi.LoggingEvent;

public class LogEvent {
	
	private String loggerName ; 
	
	private Object message; 
	
	private String threadName ; 
	
	private String className ; 
	
	private String fieldName ; 
	
	private String lineNumber ; 
	
	private String methodName ; 
	
	private int level ; 
	
	public LogEvent(LoggingEvent loggingEvent){
		this.loggerName = loggingEvent.getLoggerName() ;
		message = loggingEvent.getMessage() ; 
		threadName = loggingEvent.getThreadName() ;  
		className = loggingEvent.getLocationInformation().getClassName() ;
		fieldName = loggingEvent.getLocationInformation().getFileName() ; 
		lineNumber = loggingEvent.getLocationInformation().getLineNumber() ; 
		methodName = loggingEvent.getLocationInformation().getMethodName() ; 
		level = loggingEvent.getLevel().toInt() ; 
	}

	public String getLoggerName() {
		return loggerName;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public LogEvent() {
		super();
	}
	
	
	
}
