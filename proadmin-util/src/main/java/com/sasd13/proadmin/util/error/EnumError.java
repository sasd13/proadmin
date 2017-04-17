package com.sasd13.proadmin.util.error;

public enum EnumError {
	UNKNOWN(0, "exception.unknown"), 
	AAA(1, "exception.aaa"), 
	WS(2, "exception.ws"), 
	PARAMETERS(3, "exception.validator"), 
	PARSER(4, "exception.parser"), 
	SERVICE(5, "exception.service"), 
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
