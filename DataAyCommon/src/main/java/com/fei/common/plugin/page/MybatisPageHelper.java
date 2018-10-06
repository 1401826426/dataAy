package com.fei.common.plugin.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
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
public class MybatisPageHelper implements Interceptor{ 
	
	private static ThreadLocal<PageData> pageData = new ThreadLocal<PageData>();
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs() ;
		Executor executor = (Executor)invocation.getTarget() ;
		MappedStatement ms = (MappedStatement)args[0] ; 
		Object parameterObject = args[1]; 
		RowBounds rowBounds = (RowBounds)args[2] ; 
		@SuppressWarnings("rawtypes")
		ResultHandler resultHandler = (ResultHandler)args[3] ;
		CacheKey cacheKey = null;
		BoundSql boundSql = null; 
		if(args.length == 4){
			boundSql = ms.getBoundSql(parameterObject) ; 
			cacheKey = executor.createCacheKey(ms, parameterObject, rowBounds, boundSql) ; 
		}else if(args.length == 6){
			cacheKey = (CacheKey)args[4] ; 
			boundSql = (BoundSql)args[5] ; 
		}
		String sql = boundSql.getSql() ; 
		PageData pd = pageData.get() ; 
		if(pd != null){
			sql += " limit ?,?" ;
			List<ParameterMapping> parameterMappings = boundSql.getParameterMappings() ;
			List<ParameterMapping> pms = new ArrayList<ParameterMapping>() ;
			pms.addAll(parameterMappings) ; 
			ParameterMapping offset = new ParameterMapping.Builder(ms.getConfiguration(),"$pageOffset",int.class).build() ;
			pms.add(offset) ; 
			ParameterMapping pageNum = new ParameterMapping.Builder(ms.getConfiguration(),"$pageNum",int.class).build() ;
			pms.add(pageNum) ; 
			boundSql = new BoundSql(ms.getConfiguration(),sql,pms,parameterObject) ;
			boundSql.setAdditionalParameter("$pageOffset", pd.getOffset());
			boundSql.setAdditionalParameter("$pageNum", pd.getPageNum());
			cacheKey.update(pd);
		}
		return executor.query(ms, parameterObject, rowBounds, resultHandler, cacheKey, boundSql);
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);  
	}

	@Override
	public void setProperties(Properties properties) {
//		Object val = properties.get("defaultPageNum") ; 
//		if(val != null){
//			defaultPageNum = (int)val ; 
//		}
	}

	public static void page(int page,int pageNum){
		PageData pd = new PageData(page,pageNum) ;
		pageData.set(pd);	
	}

	
}
