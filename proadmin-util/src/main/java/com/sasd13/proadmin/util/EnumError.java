package com.sasd13.proadmin.util;

public enum EnumError {
	UNKNOWN(1, "exception.unknown"), 
	AAA(2, "exception.aaa"), 
	WS(3, "exception.ws"), 
	PARAMETERS(4, "exception.parameters"), 
	PARSER(5, "exception.parser"), 
	SERVICE(6, "exception.service"), 
	;

	private int code;
	private String bundleKey;

	private EnumError(int code, String bundleKey) {
		this.code = code;
		this.bundleKey = bundleKey;
	}

	public int getCode() {
		return code;
	}

	public String getBundleKey() {
		return bundleKey;
	}

	public static EnumError find(int code) {
		for (EnumError aaaError : values()) {
			if (code == aaaError.code) {
				return aaaError;
			}
		}

		return UNKNOWN;
	}
}
