package com.sasd13.proadmin.util;

public enum EnumRestriction {
	WHERE("where"),
	ORDER("orderBy"),
	LIMIT("limit"),
	OFFSET("offset"),;

	private String code;

	private EnumRestriction(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static EnumRestriction find(String code) {
		for (EnumRestriction restriction : values()) {
			if (restriction.code.equalsIgnoreCase(code)) {
				return restriction;
			}
		}

		return null;
	}
}
