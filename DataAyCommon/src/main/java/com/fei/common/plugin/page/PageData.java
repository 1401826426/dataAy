package com.fei.common.plugin.page;

import java.io.Serializable;

public class PageData implements Serializable{ 
	
	private static final long serialVersionUID = 1L;

	private int page ; 
	
	private int pageNum ;

	
	
	public PageData(int page) {
		super();
		this.page = page;
	}

	public PageData(int page, int pageNum) {
		super();
		this.page = page;
		this.pageNum = pageNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	} 
	
	public int getOffset(){
		return page*pageNum ; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + page;
		result = prime * result + pageNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageData other = (PageData) obj;
		if (page != other.page)
			return false;
		if (pageNum != other.pageNum)
			return false;
		return true;
	}
	
	
}
