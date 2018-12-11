package com.fei.common.cache;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.cache.CacheKey;

public class CacheKeyUtil {
	
	private static Field field ; 
	
	public static String ID_KEY = "$ID$" ;
	
	static{
		Class<?> clazz = CacheKey.class ; 
		try {
			clazz.getDeclaredField("updateList") ;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> resolveId(Object obj){
		if(!(obj instanceof CacheKey)){
			return null ;
		}
		CacheKey cacheKey = (CacheKey)obj ; 
		try {
			List<Object> updateList = (List<Object>) field.get(cacheKey) ;
			for(Object object:updateList){
				if(object instanceof String){
					String str = (String)object ; 
					if(str.startsWith(ID_KEY)){
						String[]ss = str.split(":") ;
						List<String> list = new ArrayList<>() ; 
						for(String sss:ss[1].split(",")){
							list.add(sss) ; 
						}
						return list ;  
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} 
		return null ; 
	}
	
	public static void putId(Object key,List<String> ids){
		if(key instanceof CacheKey){
			CacheKey cacheKey = (CacheKey)key ;
			StringBuilder sb = new StringBuilder("") ;
			boolean flag = false;  
			for(String id:ids){
				if(flag){
					sb.append(",") ; 
				}
				flag = true ; 
				sb.append(id) ; 
			}
			String idStr = ID_KEY+":"+sb.toString() ; 
			cacheKey.update(idStr);
		}
	}
	
}

