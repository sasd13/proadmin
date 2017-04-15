package com.sasd13.proadmin.itf.context.digitized;

import com.sasd13.proadmin.itf.context.PaginationContext;

public class DigitizedPaginationContext extends PaginationContext {

	private String paginationStartItem;
	
	public String getPaginationStartItem() {
		return paginationStartItem;
	}
	
	public void setPaginationStartItem(String paginationStartItem) {
		this.paginationStartItem = paginationStartItem;
	}
}
