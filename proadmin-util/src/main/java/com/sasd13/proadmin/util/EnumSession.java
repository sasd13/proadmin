package com.sasd13.proadmin.util;

public enum EnumSession {
	UNKNOWN("unknown"), 
	INTERMEDIARY("intermediary"), 
	START("start"), 
	TOKEN("token"), 
	USERID("uid"), 
	;

	private String name;

	private EnumSession(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static EnumSession find(String name) {
		for (EnumSession aaaSession : values()) {
			if (aaaSession.name.equalsIgnoreCase(name)) {
				return aaaSession;
			}
		}

		return UNKNOWN;
	}
}
