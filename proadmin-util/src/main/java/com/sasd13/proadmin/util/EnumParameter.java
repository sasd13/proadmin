package com.sasd13.proadmin.util;

public enum EnumParameter {
	ACADEMICLEVEL("academiclevel"), 
	CODE("code"), 
	EMAIL("email"), 
	FIRSTNAME("firstname"), 
	ID("id"), 
	LASTNAME("lastname"), 
	NUMBER("number"), 
	PASSWORD("password"), 
	PROJECT("project"), 
	REPORT("report"), 
	RUNNING("running"), 
	RUNNINGTEAM("runningteam"), 
	SESSION("session"), 
	STUDENT("student"), 
	STUDENTTEAM("studentteam"), 
	TEACHER("teacher"), 
	TEAM("team"), 
	TITLE("title"), 
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
