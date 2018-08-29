package com.fei.crawlers.spider.novel.qidian;

import java.util.List;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="https://book.qidian.com/info/{bookId}",pipelines="bookPipeline")
public class Book implements HtmlBean {

	private static final long serialVersionUID = 1L;

	@RequestParameter("bookId")
    private int bookId ;

    @Text
    @HtmlField(cssPath=".book-detail-wrap .book-information .book-info h1 em")
    private String name ;//名字

    @Text
    @HtmlField(cssPath=".book-detail-wrap .book-information .book-info h1 span a")
    private String author ;//作者
    
    @Image
    @HtmlField(cssPath=".book-detail-wrap .book-information .book-img a img")
    private String pic;//书面图片
    
    @Text
    @HtmlField(cssPath=".book-detail-wrap .book-information .book-info p span")
    private List<String> tags  ;//标签

    @Text
    @HtmlField(cssPath=".book-detail-wrap .book-information .book-info p a")
    private List<String> cats ;//分类

    @Text
    @HtmlField(cssPath=".book-detail-wrap .book-content-wrap .left-wrap .book-info-detail .book-intro p")
    private String desc ;//简介
  

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCats() {
        return cats;
    }

    public void setCats(List<String> cats) {
        this.cats = cats;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public static void main(String[] args){
        GeccoEngine.create()
                .classpath("com.fei.crawlers.novel.qidian")
                .start("https://book.qidian.com/info/1004608738")
                .thread(10)
                .interval(2000)
                .debug(true)
                .loop(false)
                .mobile(false)
                .start();

    }

}

