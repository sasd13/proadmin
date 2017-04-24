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
@JsonPropertyOrder({ "languageISOCode", "paginationStartItem", "paginationWantedItems", "additionalProperties" })
public class SearchContext {

	@JsonProperty("languageISOCode")
	private String languageISOCode;

	@JsonProperty("paginationStartItem")
	private String paginationStartItem;

	@JsonProperty("paginationWantedItems")
	private String paginationWantedItems;

	@JsonProperty("additionalProperties")
	private Map<String, Object> additionalProperties;

	public SearchContext() {
		additionalProperties = new HashMap<>();
	}

	public String getLanguageISOCode() {
		return languageISOCode;
	}

	public void setLanguageISOCode(String languageISOCode) {
		this.languageISOCode = languageISOCode;
	}

	public String getPaginationStartItem() {
		return paginationStartItem;
	}

	public void setPaginationStartItem(String paginationStartItem) {
		this.paginationStartItem = paginationStartItem;
	}

	public String getPaginationWantedItems() {
		return paginationWantedItems;
	}

	public void setPaginationWantedItems(String paginationWantedItems) {
		this.paginationWantedItems = paginationWantedItems;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
}