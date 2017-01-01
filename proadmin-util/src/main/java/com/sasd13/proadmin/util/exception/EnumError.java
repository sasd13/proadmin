package com.sasd13.proadmin.util.exception;

public enum EnumError {
	CONNECTION(0, "exception.connection"), 
	UNKNOWN(1, "exception.unknown"), 
	AAA(100, "exception.aaa"), 
	AAA_LOGIN(101, "exception.aaa.login"), 
	PARSER(200, "exception.parser"), 
	VALIDATOR(300, "exception.validator"), 
	BUSINESS(400, "exception.business"),
	SERVICE(500, "exception.service"), 
	WEB_SERVICE(600, "exception.ws"), 
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
