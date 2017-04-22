package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {

	private String languageISOCode, paginationWantedItems;
	private Map<String, Object> additionalProperties;

	public RequestContext() {
		additionalProperties = new HashMap<>();
	}

	public String getLanguageISOCode() {
		return languageISOCode;
	}

	public void setLanguageISOCode(String languageISOCode) {
		this.languageISOCode = languageISOCode;
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