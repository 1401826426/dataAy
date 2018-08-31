package com.fei.common.zookeeper.test;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeperMain;

public class JustZookeeperClient {
	
	public static void main(String[] args) throws KeeperException, IOException, InterruptedException {
		args = new String[]{
				"-server","localhost:2181"
		};
		ZooKeeperMain.main(args);
	}
	
}
