package com.fei.crawlers.util;

import java.util.ArrayList;
import java.util.List;

import com.luhuiguo.fastdfs.conn.FdfsConnectionPool;
import com.luhuiguo.fastdfs.conn.TrackerConnectionManager;
import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.DefaultFastFileStorageClient;
import com.luhuiguo.fastdfs.service.DefaultTrackerClient;

public class FastDfsTest {
	
	public static void main(String[] args){
		FdfsConnectionPool fdfsConnectionPool = new FdfsConnectionPool() ; 
		List<String> trackerList = new ArrayList<>() ; 
		trackerList.add("120.79.54.108:23000") ; 
		TrackerConnectionManager trackerConnectionManager = new TrackerConnectionManager(fdfsConnectionPool, trackerList) ; 
		DefaultTrackerClient trackerClient = new DefaultTrackerClient(trackerConnectionManager) ;
		DefaultFastFileStorageClient storageClient = new DefaultFastFileStorageClient(trackerClient,trackerConnectionManager) ;
		byte[] bytes = new byte[]{1,1,1,1,1,1,1,1,1,1} ; 
		StorePath path = storageClient.uploadFile(bytes,"test") ;
		System.out.println(path.getFullPath());
		
	}
	
	
}













