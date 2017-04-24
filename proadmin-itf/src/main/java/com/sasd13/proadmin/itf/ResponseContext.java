package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

public class ResponseContext {

	private String languageISOCode, paginationTotalItems;
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