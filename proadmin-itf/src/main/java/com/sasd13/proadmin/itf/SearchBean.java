package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({ "context", "criterias" })
public class SearchBean {

	@JsonProperty("context")
	private SearchContext context;

	@JsonProperty("criterias")
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
