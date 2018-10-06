package com.fei.common.rank;

import java.util.List;

import com.fei.common.converter.Converter;
import com.fei.common.converter.ConverterException;
import com.fei.common.redis.IJedisSortSet;

@SuppressWarnings("unused")
public class RankManager {
	
	private Converter converter ;
	
	
	private IJedisSortSet jedisSortSet ;

	public void setConverter(Converter converter) {
		this.converter = converter;
	}

	public void setJedisSortSet(IJedisSortSet jedisSortSet) {
		this.jedisSortSet = jedisSortSet;
	} 
	
	public <K,V> List<V> getTopList(String key){
		try {
			byte[] keyBytes = converter.writeValue(key);
//			Set<byte[]> sets = jedisSortSet.zrange(keyBytes,0, 1000) ;
//			List<V> list = new ArrayList<V>() ; 
//			for(byte[] bytes:sets){
				
//			}
		} catch (ConverterException e) {
			e.printStackTrace();
		}
		return null ; 
	}
	
}
