package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

public class ResponseContext {

	private String languageISOCode, paginationWantedItems, paginationCurrentItems, paginationTotalItems;
	private Map<String, String[]> additionalProperties;

	public ResponseContext() {
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

	public String getPaginationCurrentItems() {
		return paginationCurrentItems;
	}

	public void setPaginationCurrentItems(String paginationCurrentItems) {
		this.paginationCurrentItems = paginationCurrentItems;
	}

	public String getPaginationTotalItems() {
		return paginationTotalItems;
	}

	public void setPaginationTotalItems(String paginationTotalItems) {
		this.paginationTotalItems = paginationTotalItems;
	}

	public Map<String, String[]> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, String[]> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
}