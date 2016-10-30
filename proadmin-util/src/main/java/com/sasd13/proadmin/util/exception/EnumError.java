package com.sasd13.proadmin.util.exception;

public enum EnumError {
	UNKNOWN(0, "error.unknown"), 
	AAA(1, "exception.aaa"), 
	DATA_PARSING(2, "exception.data_parsing"), 
	DATA_VALIDATING(3, "exception.data_validating"), 
	SERVICE(4, "exception.service"), 
	WEBSERVICE(5, "exception.ws"), 
	USER_LOGIN_FAILED(100, "error.user_login_failed"), 
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
