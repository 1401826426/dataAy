package com.fei.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fei.admin.mapper.BookMapper;
import com.fei.admin.pojo.Book;
import com.fei.admin.pojo.BookExample;
import com.fei.admin.service.BaseService;
import com.fei.admin.service.IBookService;
import com.fei.common.Response;
import com.fei.common.plugin.page.MybatisPageHelper;

@Service
public class BookService extends BaseService implements IBookService{

	@Autowired
	private BookMapper bookMapper ; 	
	
	@Override
	public Response get(int id) {
		Book book = bookMapper.selectByPrimaryKey(id) ; 
		return Response.ok(book);
	}

	@Override
	public Response getAll(int cid, int page, int pageNum) {
		if(cid < 0 || page < 0 || pageNum < 0){
			return Response.fail("error parameter") ; 
		}
		MybatisPageHelper.page(page, pageNum);
		BookExample example = new BookExample();   
		List<Book> books = bookMapper.selectByExample(example) ;
		return Response.ok(books);
	}

}
