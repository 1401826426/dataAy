package com.fei.common.rpc.dto;

public class CrawlerMonitorDto {
	
	private int threadNum ;

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	@Override
	public String toString() {
		return "CrawlerMonitorDto [threadNum=" + threadNum + "]";
	} 
	
	
	
}
