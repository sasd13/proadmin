package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

public class SearchBean {

	private SearchContext context;
	private Map<String, String[]> criterias;

	public SearchBean() {
		context = new SearchContext();
		criterias = new HashMap<>();
	}

	public SearchContext getContext() {
		return context;
	}

	public void setContext(SearchContext context) {
		this.context = context;
	}

	public Map<String, String[]> getCriterias() {
		return criterias;
	}

	public void setCriterias(Map<String, String[]> criterias) {
		this.criterias = criterias;
	}
}
