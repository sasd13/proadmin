package com.sasd13.proadmin.itf.context.dated;

import com.sasd13.proadmin.itf.context.Context;

public class DatedContext extends Context {

	private DatedPaginationContext paginationContext;
	
	public DatedPaginationContext getPaginationContext() {
		return paginationContext;
	}
	
	public void setPaginationContext(DatedPaginationContext paginationContext) {
		this.paginationContext = paginationContext;
	}
}
