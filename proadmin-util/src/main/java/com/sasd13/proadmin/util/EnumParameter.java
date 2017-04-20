package com.sasd13.proadmin.util;

public enum EnumParameter {
	ACADEMICLEVEL("academiclevel"),
	CODE("code"),
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
	START_DATE("startDate"),
	END_DATE("endDate"),
	STUDENT("student"),
	TEACHER("teacher"),
	TEAM("team"),
	USERID("uid"),
	YEAR("year"),;

	private String name;

	private EnumParameter(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static EnumParameter find(String name) {
		for (EnumParameter parameter : values()) {
			if (parameter.name.equalsIgnoreCase(name)) {
				return parameter;
			}
		}

		return null;
	}
}
