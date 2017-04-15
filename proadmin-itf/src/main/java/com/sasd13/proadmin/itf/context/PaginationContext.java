package com.sasd13.proadmin.itf.context;

public class PaginationContext {

	private String wantedItems, currentItems, totalItems;
	
	public String getWantedItems() {
		return wantedItems;
	}
	
	public void setWantedItems(String wantedItems) {
		this.wantedItems = wantedItems;
	}
	
	public String getCurrentItems() {
		return currentItems;
	}
	
	public void setCurrentItems(String currentItems) {
		this.currentItems = currentItems;
	}
	
	public String getTotalItems() {
		return totalItems;
	}
	
	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}
}
