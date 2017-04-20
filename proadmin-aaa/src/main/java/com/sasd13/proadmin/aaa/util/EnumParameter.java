package com.sasd13.proadmin.aaa.util;

public enum EnumParameter {
	ID("id"), 
	INTERMEDIARY("intermediary"), 
	PASSWORD("password"), 
	USERID("userid"), 
	USERNAME("username"), 
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
