package com.sasd13.proadmin.util;

public enum EnumCriteria {
	ACADEMICLEVEL("academiclevel"),
	CODE("code"),
	DATE("date"),
	EMAIL("email"),
	FIRSTNAME("firstname"),
	INTERMEDIARY("intermediary"),
	LASTNAME("lastname"),
	NAME("name"),
	NUMBER("number"),
	PROJECT("project"),
	REPORT("report"),
	RUNNING("running"),
	SESSION("session"),
	STUDENT("student"),
	TEACHER("teacher"),
	TEAM("team"),
	USERID("uid"),
	YEAR("year"),;

	private String code;

	private EnumCriteria(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static EnumCriteria find(String code) {
		for (EnumCriteria criteria : values()) {
			if (criteria.code.equalsIgnoreCase(code)) {
				return criteria;
			}
		}

		return null;
	}
}
