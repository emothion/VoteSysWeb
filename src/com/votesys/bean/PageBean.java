package com.votesys.bean;

/**
 * @ClassName com.votesys.bean.PageBean.java
 * @Description 分页工具数据模型
 */
public class PageBean {

	private int page; // 第几页
	private int pageSize; // 每页记录数
	@SuppressWarnings("unused")
	private int start; // 起始页


	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return page * pageSize+1;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		int startPoint;
		if ((page-1) * pageSize == 0) {
			startPoint = 1;
		} else {
			startPoint = (page-1) * pageSize+1;
		}
		return startPoint;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
