package com.sasd13.proadmin.util.exception;

public enum EnumError {
	UNKNOWN(0, "error.unknown"), 
	AAA(1, "exception.aaa"), 
	AAA_LOGIN_FAILED(100, "error.aaa.login.failed"), 
	PARSING_DATA(2, "exception.parsing.data"), 
	VALIDATING_DATA(3, "exception.validating.data"), 
	SERVICE(4, "exception.service"), 
	WEB_SERVICE(5, "exception.ws"), 
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
