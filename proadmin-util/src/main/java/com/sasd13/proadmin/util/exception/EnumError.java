package com.sasd13.proadmin.util.exception;

public enum EnumError {
	UNKNOWN(0, "error.unknown"), 
	AAA(1, "exception.aaa"), 
	AAA_LOGIN_FAILED(100, "exception.aaa.login.failed"), 
	PARSER(2, "exception.parser"), 
	VALIDATOR(3, "exception.validator"), 
	SERVICE(4, "exception.service"), 
	WEB_SERVICE(5, "exception.webservice"), 
	BUSINESS(6, "exception.business"),
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
