package com.fei.crawlers.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Before;
import org.junit.Test;

import com.fei.crawlers.pojo.CatBook;

public class TestMapper {
	
	
	private SqlSessionManager sqlSessionManager ;
	
	@Before
	public void before(){
		sqlSessionManager = SqlSessionManager.newInstance(TestMapper.class.getClassLoader().getResourceAsStream("test-mybatis-configure.xml")) ; 
	}
	
	@Test
	public void testInsertCatBooks(){
		CatBookMapper catBookMapper = sqlSessionManager.getMapper(CatBookMapper.class) ;
		List<CatBook> catBooks = new ArrayList<>() ; 
		CatBook catBook = new CatBook() ; 
		catBook.setBookId(1);
		catBook.setCatId(2);
		catBooks.add(catBook) ; 
		catBook = new CatBook() ; 
		catBook.setBookId(2);
		catBook.setCatId(3);
		catBooks.add(catBook) ; 
		catBookMapper.insertCatBooks(catBooks);
	}
	
	
}
