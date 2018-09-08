package com.fei.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fei.admin.mapper.BookMapper;
import com.fei.admin.pojo.Book;
import com.fei.admin.service.BaseService;
import com.fei.admin.service.IBookService;
import com.fei.common.Response;



@Service
public class BookService extends BaseService implements IBookService{

	@Autowired
	private BookMapper bookMapper ; 
	
	@Override
	public Response get(int id) {
		Book book = bookMapper.selectByPrimaryKey(id) ; 
		return Response.ok(book);
	}

}
