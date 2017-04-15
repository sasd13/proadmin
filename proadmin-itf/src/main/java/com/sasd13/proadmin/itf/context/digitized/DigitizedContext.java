package com.sasd13.proadmin.itf.context.digitized;

import com.sasd13.proadmin.itf.context.Context;

public class DigitizedContext extends Context {

	private DigitizedPaginationContext paginationContext;
	
	public DigitizedPaginationContext getPaginationContext() {
		return paginationContext;
	}
	
	public void setPaginationContext(DigitizedPaginationContext paginationContext) {
		this.paginationContext = paginationContext;
	}
}
