package com.sasd13.proadmin.util;

public enum EnumPreference {
	GENERAL_DATE("GEN", "DT"), ;

	private String category, name;

	private EnumPreference(String category, String name) {
		this.category = category;
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}
}
