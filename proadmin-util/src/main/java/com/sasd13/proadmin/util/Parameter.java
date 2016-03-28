package com.sasd13.proadmin.util;

public enum Parameter {
	
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
	SESSIONNUMBER("sessionnumber"),
	STUDENT("student"),
	STUDENTTEAM("studentteam"),
	TEACHER("teacher"),
	TEAM("team"),
	TITLE("title"),
	YEAR("year");
	
	private String name;
	
	private Parameter(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
