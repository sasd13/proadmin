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
	STUDENT("student"), 
	TEACHER("teacher"), 
	USERID("uid"), 
	TEAM("team"), 
	YEAR("year"),
	;

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
