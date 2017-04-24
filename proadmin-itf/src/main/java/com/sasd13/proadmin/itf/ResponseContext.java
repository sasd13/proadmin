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
@JsonPropertyOrder({ "languageISOCode", "paginationTotalItems", "additionalProperties" })
public class ResponseContext {

	@JsonProperty("languageISOCode")
	private String languageISOCode;

	@JsonProperty("paginationTotalItems")
	private String paginationTotalItems;

	@JsonProperty("additionalProperties")
	private Map<String, Object> additionalProperties;

	public ResponseContext() {
		additionalProperties = new HashMap<>();
	}

	public String getLanguageISOCode() {
		return languageISOCode;
	}

	public void setLanguageISOCode(String languageISOCode) {
		this.languageISOCode = languageISOCode;
	}

	public String getPaginationTotalItems() {
		return paginationTotalItems;
	}

	public void setPaginationTotalItems(String paginationTotalItems) {
		this.paginationTotalItems = paginationTotalItems;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
}