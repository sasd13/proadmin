package com.sasd13.proadmin.core.util;

public enum EnumURLParameter {
	
	ACADEMICLEVEL("academiclevel"),
	CODE("code"),
	EMAIL("email"),
	FIRSTNAME("firstname"),
	LASTNAME("lastname"),
	NUMBER("number"),
	PROJECT("project"),
	REPORT("report"),
	RUNNING("running"),
	STUDENT("student"),
	TEACHER("teacher"),
	TEAM("team"),
	TITLE("title"),
	WEEK("week"),
	YEAR("year");
	
	private String name;
	
	EnumURLParameter(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
