package com.fei.common.plugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

public class MybatisAutoIdHelper implements Interceptor{
	
	private static Method proxyMethod = null; 
	static{
		try {
			proxyMethod = Executor.class.getMethod("update", MappedStatement.class,Object.class) ;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		return null;
	}

	@Override
	public Object plugin(Object target) {
		if(target == null){
			return null ; 
		}
		if(target instanceof Executor && proxyMethod != null){
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class<?>[]{Executor.class},new ExecutorInvocationHandler(target)) ; 
		}
		return target;
	}

	private class ExecutorInvocationHandler implements InvocationHandler{
		
		private Object target ; 
		
		public ExecutorInvocationHandler(Object target){
			this.target = target ; 
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if(method.equals(proxyMethod)){
				MappedStatement mappedStatement = (MappedStatement)args[0] ; 
				if(mappedStatement != null){
					Field field = MappedStatement.class.getDeclaredField("keyGenerator") ; 
					field.setAccessible(true);
					KeyGenerator keyGenerator = (KeyGenerator) field.get(mappedStatement) ; 
					if(keyGenerator == null || keyGenerator instanceof NoKeyGenerator){
						try{
							field.set(mappedStatement,new Jdbc3KeyGenerator());
							method.invoke(target, args) ; 
						}finally {
							field.set(mappedStatement,keyGenerator);
						}
					}
				}
			}
			return method.invoke(target, args);
		}
		
	}
	
	@Override
	public void setProperties(Properties properties) {
		
	}

}
