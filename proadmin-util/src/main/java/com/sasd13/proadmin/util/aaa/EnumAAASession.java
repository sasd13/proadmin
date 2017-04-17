package com.sasd13.proadmin.util.aaa;

public enum EnumAAASession {
	UNKNOWN("unknown"), 
	INTERMEDIARY("intermediary"), 
	START("start"), 
	TOKEN("token"), 
	USERID("uid"), 
	;

	private String name;

	private EnumAAASession(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static EnumAAASession find(String name) {
		for (EnumAAASession aaaSession : values()) {
			if (aaaSession.name.equalsIgnoreCase(name)) {
				return aaaSession;
			}
		}

		return UNKNOWN;
	}
}
