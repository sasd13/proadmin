package com.sasd13.proadmin.util.net;

public enum EnumAAASessionInfo {
	UNKNOWN("unknown"), 
	USERNAME("username"),
	TOKEN("token"),
	BEGIN("begin"),
	;

	private String name;

	private EnumAAASessionInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static EnumAAASessionInfo find(String name) {
		for (EnumAAASessionInfo aaaSessionInfo : values()) {
			if (aaaSessionInfo.name.equalsIgnoreCase(name)) {
				return aaaSessionInfo;
			}
		}

		return UNKNOWN;
	}
}
