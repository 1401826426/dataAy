package com.fei.common.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import util.str.StringUtils;

public abstract class AbstractZookeeperCenter {
	
	private ZooKeeper zk ; 
	
	//TODO
	//watcher 无回调问题
	private class ZookeeperCenterWatcher implements Watcher{

		@Override
		public void process(WatchedEvent event) {
//			EventType type = event.getType() ;  
			refresh() ; 
//			if(EventType.NodeCreated == type || EventType.NodeChildrenChanged == type
//					|| EventType.NodeDeleted == type){
//				
//			}
		}
	}
		
	protected abstract void refresh() ; 
	
	protected abstract String loadConnectString()  ; 
	
	public void ini(){
		String connectString = loadConnectString() ; 
		try {
			zk = new ZooKeeper(connectString,2000,new ZookeeperCenterWatcher()) ;
		} catch (IOException e) {
			throw new RuntimeException(e) ; 
		} 
	}
	
	
	

	public void createDataRecursive(String path,byte[] data,List<ACL> acls,CreateMode mode) throws KeeperException, InterruptedException{ 
		for(int index = path.indexOf("/",0);;index=path.indexOf("/", index+1)){
			String subPath = "" ; 
			if(index == -1){
				subPath = path ; 
			}else{				
				subPath = path.substring(0,index) ;
			}
			if(StringUtils.isBlank(subPath)){//以/开头的
				if(index == -1){
					break; 
				}else{
					continue ; 					
				}
			}
			try{
				if(index == -1){					
					zk.create(subPath, data,acls,mode) ; 
				}else{
					zk.create(subPath, data,acls,CreateMode.PERSISTENT);
				}
			}catch(KeeperException e){
				if(e.code() != Code.NODEEXISTS){
				    throw e ; 
				}
			}
			if(index == -1){
				break ; 
			}
		}
	}
	
	public List<String> getChild(String path){
		List<String> list = null;
		try {
			list = zk.getChildren(path, false);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list ; 
	}
	
	 public byte[] getData(String path){
		 try {
			return zk.getData(path, false, null);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		} 
		return null ; 
	 }
	
}
