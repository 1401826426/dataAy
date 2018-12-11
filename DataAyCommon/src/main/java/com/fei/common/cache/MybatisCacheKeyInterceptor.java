
package com.fei.common.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({
	@Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class,CacheKey.class,BoundSql.class}),
	@Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})
})
public class MybatisCacheKeyInterceptor implements Interceptor{

	private Pattern selectPattern = Pattern.compile("^\\$select.*In") ; 
	
	public void $test(){
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Executor executor = (Executor)invocation.getTarget() ; 
		Object[] args = invocation.getArgs() ; 
		MappedStatement ms = (MappedStatement)args[0]; 
		Object parameterObject = args[1] ; 
		RowBounds rws = (RowBounds)args[2] ; 
		ResultHandler rh = (ResultHandler)args[3] ; 
		CacheKey cacheKey = null ; 
		BoundSql boundSql = null ; 
		if(args.length == 4){
			boundSql = ms.getBoundSql(parameterObject) ; 
			cacheKey = executor.createCacheKey(ms, parameterObject, rws, boundSql) ; 
		}else{
			cacheKey = (CacheKey)args[4] ; 
			boundSql = (BoundSql)args[5] ; 
		}
		SqlCommandType sqlCommandType = ms.getSqlCommandType() ; 
		if(sqlCommandType == SqlCommandType.SELECT){
			String id = ms.getId() ;
			if(selectPattern.matcher(id).find()){
				List<Number> list = (List<Number>)parameterObject  ;
				List<String> idList = new ArrayList<>(list.size()) ; 
				for(Number num:list){
					idList.add(num.toString()) ; 
				}
				CacheKeyUtil.putId(cacheKey, idList);
			}
		}
		return executor.query(ms,parameterObject, rws, rh, cacheKey, boundSql);
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this) ;
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

	
}
