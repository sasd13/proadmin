package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "header", "criterias" })
public class SearchBean {

	@JsonProperty("header")
	private SearchHeader header;

	@JsonProperty("criterias")
	private Map<String, String[]> criterias;

	public SearchBean() {
		header = new SearchHeader();
		criterias = new HashMap<>();
	}

	public SearchHeader getHeader() {
		return header;
	}

	public void setHeader(SearchHeader header) {
		this.header = header;
	}

	public Map<String, String[]> getCriterias() {
		return criterias;
	}

	public void setCriterias(Map<String, String[]> criterias) {
		this.criterias = criterias;
	}
}
