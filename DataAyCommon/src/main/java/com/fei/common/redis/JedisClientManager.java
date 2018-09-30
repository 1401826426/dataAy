package com.fei.common.redis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BasicCommands;
import redis.clients.jedis.BinaryJedisClusterCommands;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.MultiKeyBinaryJedisClusterCommands;
import redis.clients.jedis.MultiKeyJedisClusterCommands;

public class JedisClientManager implements InvocationHandler{
	
	private JedisPool jedisPool ; 
	
	private JedisCluster jedisCluster ;
	
	private Set<Class<?>> useInterface = new HashSet<>() ;
	
	private Object proxy ;
	
	private Map<Method,Method> toJedisInterface = new HashMap<>() ; 
	
	private Map<Method,Method> toJedisClusterInterface = new HashMap<>() ; 
	
	public JedisClientManager(JedisCluster jedisCluster){
		this(null,jedisCluster,null) ; 
	}
	
	public JedisClientManager(JedisPool jedisPool){
		this(jedisPool,null,null) ;  
	}
	
	public JedisClientManager(JedisPool jedisPool,Class<?>[] clazzs){
		this(jedisPool,null,clazzs) ;  
	}
	
	public JedisClientManager(JedisCluster jedisCluster,Class<?>[]clazzs){
		this(null,jedisCluster,clazzs) ; 
	}
	
	public JedisClientManager(JedisPool jedisPool,JedisCluster jedisCluster,Class<?>[] clazzs){
		this.jedisCluster = jedisCluster;
		this.jedisPool = jedisPool ; 
		setInterfaces(clazzs);
	}


    public void setInterfaces(Class<?>[] interfaces){
    	if(interfaces == null){
    		return ; 
    	}
    	for(Class<?> clazz:interfaces){
    		addInterface(clazz) ; 
    	}
    }
    
    public void setInterface(Class<?> clazz){
    	addInterface(clazz);
    }
    
	private void addInterface(Class<?> clazz) {
		if(clazz == null){
			return ; 
		}
		if(!clazz.isInterface()){
			throw new RuntimeException("not interface") ;
		}
		if(useInterface.contains(clazz)){
			return ; 
		}
		Map<Method,Method> jedisMap = new HashMap<>() ;
		Map<Method,Method> jedisClusterMap = new HashMap<>() ;
		Class<?> jedisClazz = Jedis.class ; 
		Class<?> jedisCluster = JedisCluster.class ; 
		for(Method method:clazz.getMethods()){
			try{
				Method jedisMethod = jedisClazz.getMethod(method.getName(),method.getParameterTypes()) ;
			    jedisMap.put(method, jedisMethod) ;
			    Method jedisClusterMethod = jedisCluster.getMethod(method.getName(), method.getParameterTypes()) ; 
			    jedisClusterMap.put(method, jedisClusterMethod) ; 
			}catch(NoSuchMethodException e){
				throw new RuntimeException("no method name=" + method.getName() + ",pts="+method.getParameterTypes()) ; 
			}
		}
		this.toJedisInterface.putAll(jedisMap);
		this.toJedisClusterInterface.putAll(jedisClusterMap);
		this.useInterface.add(clazz) ;
	}

	
	@SuppressWarnings("unchecked")
	public <T> T getInterface(Class<T> interace){
    	if(!useInterface.contains(interace)){
    		throw new RuntimeException("illegal interface" + interace); 
    	}
    	checkCreateProxy() ;
    	return (T)proxy;  
    }
    
	private void checkCreateProxy() {
		if(proxy == null){
			synchronized (this) {
				if(proxy == null){
					createProxy() ; 
				}
			}
		}
	}

	private void createProxy() {
		if(jedisCluster == null && jedisPool == null){
			throw new RuntimeException("no jedis") ; 
		}
		Class<?>[] interfaces = new Class<?>[this.useInterface.size()] ; 
		this.useInterface.toArray(interfaces) ; 
		this.proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(jedisPool != null){
			Jedis jedis = null ; 
			try{
				jedis = jedisPool.getResource() ; 
				Method jedisMethod = toJedisInterface.get(method) ;  
				return jedisMethod.invoke(jedis, args);
			}catch(Exception e){
				throw e ; 
			}finally{
				if(jedis != null){
					jedis.close();  
				}
			}
		}else if(jedisCluster != null){
			Method jedisClusterMethod = toJedisClusterInterface.get(method) ;   
			return jedisClusterMethod.invoke(jedisCluster, args) ; 
		}else{
			throw new RuntimeException("no jedis") ;
		}
		
	}
	
	public static void main(String[] args) {
		JedisClientManager jedisClientManager = new JedisClientManager(new JedisPool("127.0.0.1",6379)) ;
		jedisClientManager.addInterface(JedisCommands.class);
//		jedisClientManager.addInterface(MultiKeyCommands.class);
//		jedisClientManager.addInterface(AdvancedJedisCommands.class);
//		jedisClientManager.addInterface(ScriptingCommands.class);
		jedisClientManager.addInterface(BasicCommands.class);
//		jedisClientManager.addInterface(BinaryJedisCommands.class);
//		jedisClientManager.addInterface(MultiKeyBinaryCommands.class);
//		jedisClientManager.addInterface(AdvancedBinaryJedisCommands.class);
//		jedisClientManager.addInterface(BinaryScriptingCommands.class);
//		jedisClientManager.addInterface(ClusterCommands.class);
		jedisClientManager.addInterface(MultiKeyJedisClusterCommands.class);
//		jedisClientManager.addInterface(JedisClusterScriptingCommands.class);
		jedisClientManager.addInterface(BinaryJedisClusterCommands.class);
		jedisClientManager.addInterface(MultiKeyBinaryJedisClusterCommands.class);
//		jedisClientManager.addInterface(JedisClusterBinaryScriptingCommands.class);
	}
	
	
	
}














