package com.sasd13.proadmin.util.ws;

public enum EnumAAASession {
	UNKNOWN("unknown"), 
	USERNAME("username"),
	TOKEN("token"),
	BEGIN("begin"),
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
