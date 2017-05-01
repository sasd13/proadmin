package com.sasd13.proadmin.util;

public enum EnumSession {
	UNKNOWN("unknown"),
	INTERMEDIARY("intermediary"),
	START("start"),
	TOKEN("token"),
	USERID("uid"),;

	private String key;

	private EnumSession(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static EnumSession find(String key) {
		for (EnumSession session : values()) {
			if (session.key.equalsIgnoreCase(key)) {
				return session;
			}
		}

		return UNKNOWN;
	}
}
