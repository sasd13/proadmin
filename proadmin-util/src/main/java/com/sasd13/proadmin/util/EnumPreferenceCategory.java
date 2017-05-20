package com.sasd13.proadmin.util;

public enum EnumPreferenceCategory {
	GENERAL("GEN"), ;

	private String code;

	private EnumPreferenceCategory(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static EnumPreferenceCategory find(String code) {
		for (EnumPreferenceCategory category : values()) {
			if (category.code.equalsIgnoreCase(code)) {
				return category;
			}
		}

		return null;
	}
}
