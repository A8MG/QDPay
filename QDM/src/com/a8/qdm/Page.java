package com.a8.qdm;

/**
 * 分页对象
 * 
 * @author Lund
 * 
 */
public class Page {

	/**
	 * 开始记录索引
	 */
	private int startIndex;

	/**
	 * 当前页数
	 */
	private int currentPage = 1;

	/**
	 * 每页条数
	 */
	private int pageSize = 10;

	/**
	 * 总页数
	 */
	private int pageCount;

	/**
	 * 总记录
	 */
	private int totalCount;

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		this.startIndex = (currentPage - 1) * pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount() {
		this.pageCount = (totalCount + pageSize - 1) / pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return startIndex + "|" + currentPage + "|" + pageSize + "|"
				+ pageCount + "|" + totalCount;
	}
}
