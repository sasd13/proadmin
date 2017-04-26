package com.sasd13.proadmin.itf;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({ "applicativeContext" })
public class SearchHeader {

	@JsonProperty("applicativeContext")
	private SearchApplicativeContext applicativeContext;

	public SearchHeader() {
		applicativeContext = new SearchApplicativeContext();
	}

	public SearchApplicativeContext getApplicativeContext() {
		return applicativeContext;
	}

	public void setApplicativeContext(SearchApplicativeContext applicativeContext) {
		this.applicativeContext = applicativeContext;
	}
}