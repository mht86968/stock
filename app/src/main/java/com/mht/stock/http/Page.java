package com.mht.stock.http;

public class Page {

	public static final int PAGE_SIZE = 20;

	private int size = PAGE_SIZE;
	private int page = 0;
	private int total;

	public void onResult(Result result) {
		this.total = result.getTotal();
	}

	public void nextPage() {
		page++;
	}

	public int getPageIndex() {
		return page;
	}

	public int getPageSize() {
		return size;
	}
	
	@Deprecated
	public boolean isLastPage() {
		return page*size <= total ? true : false;
	}
	
	public void setPageSize(int size) {
		this.size = size;
	}
}