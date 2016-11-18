package com.sasd13.proadmin.aaa.util;

import com.sasd13.javaex.conf.AppProperties;

public enum EnumParameter {
	UNKNOWN("unknown"), 
	USERNAME(AppProperties.getProperty(Names.AAA_REQUEST_LOGIN_PARAMETER_USERNAME)), 
	PASSWORD(AppProperties.getProperty(Names.AAA_REQUEST_LOGIN_PARAMETER_PASSWORD)),
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

		return UNKNOWN;
	}
}
