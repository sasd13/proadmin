package com.sasd13.proadmin.itf.context.dated;

import com.sasd13.proadmin.itf.context.PaginationContext;

public class DatedPaginationContext extends PaginationContext {

	private String startDateISO8601;
	
	public String getStartDateISO8601() {
		return startDateISO8601;
	}
	
	public void setStartDateISO8601(String startDateISO8601) {
		this.startDateISO8601 = startDateISO8601;
	}
}
