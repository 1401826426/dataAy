package com.fei.crawlers.spider.novel.qidian;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fei.crawlers.mapper.BookMapper;
import com.fei.crawlers.mapper.CatBookMapper;
import com.fei.crawlers.mapper.CatMapper;
import com.fei.crawlers.pojo.Cat;
import com.fei.crawlers.pojo.CatBook;
import com.fei.crawlers.pojo.CatExample;
import com.fei.crawlers.pojo.CatExample.Criteria;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.SpiderBean;

import util.collection.CollectionUtils;

@PipelineName("bookPipeline")
@Component("bookPipeline")
public class BookPipeline implements Pipeline<SpiderBean>{
	
	@Autowired
	private BookMapper bookMapper; 
	
	@Autowired
	private CatMapper catMapper ; 
	
	@Autowired
	private CatBookMapper catBookMapper ; 
	
	@Override
	public void process(SpiderBean bean) {
		if(bean instanceof Book){
			Book book = (Book)bean ;  
			saveBook(book) ; 
		}
	}

	@Transactional
	private void saveBook(Book book) {
		List<String> cats = book.getCats() ;
		List<CatBook> catBooks = new ArrayList<>() ;
		com.fei.crawlers.pojo.Book pojoBook = new com.fei.crawlers.pojo.Book() ; 
		pojoBook.readFrom(book) ;
		pojoBook.setContent(QidianBookUrlGenerator.generateBookDownLoadUrl(book.getBookId()));
		bookMapper.insert(pojoBook) ; 
		if(!CollectionUtils.isEmpty(cats)){
			String name = cats.get(0) ; 
			Cat cat = saveIfObsent(name,0) ; 
			int id = cat.getId(); 
			CatBook catBook = new CatBook() ; 
			catBook.setBookId(pojoBook.getId()); 
			catBook.setCatId(id);
			catBooks.add(catBook) ; 
			if(cats.size() >= 2){
				name = cats.get(1) ; 
				Cat childCat = saveIfObsent(name,id) ; 
				CatBook childCatBook = new CatBook() ; 
				childCatBook.setBookId(pojoBook.getId());
				childCatBook.setCatId(childCat.getId());
				catBooks.add(childCatBook) ; 
 			}
 		}
		catBookMapper.insertCatBooks(catBooks) ; 
	}

	private Cat saveIfObsent(String name, int parentId) {
		CatExample catExample = new CatExample() ;
		Criteria creteria = catExample.createCriteria() ; 
		creteria.andNameEqualTo(name) ;
		Cat cat  = catMapper.selectOneByExample(catExample) ;  
		if(cat == null){
			cat = new Cat() ; 
			cat.setName(name);
			cat.setParentId(parentId);
			catMapper.insert(cat) ; 
		}
		return cat ; 
	}

}
