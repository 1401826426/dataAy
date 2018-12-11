package com.fei.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fei.admin.service.IBookService;
import com.fei.common.Response;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	IBookService bookService ;
	
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	public Response get(@PathVariable("id") int id){
		return bookService.get(id) ; 
	}
	
	@RequestMapping(value="/get/all/{cid}/{page}/{pageNum}",method=RequestMethod.GET)
	public Response getAll(@PathVariable("cid") int cid , @PathVariable("page") int page,@PathVariable("pageNum") int pageNum){
		return bookService.getAll(cid,page,pageNum) ; 
	}
		
	
	
}





