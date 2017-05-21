package com.sasd13.proadmin.util;

public enum EnumError {
	UNKNOWN(1, "exception.unknown"),
	AUTHENTICATION(2, "exception.authentication"),
	AAA(3, "exception.aaa"),
	WS(4, "exception.ws"),
	PARAMETERS(5, "exception.parameters"),
	PARSER(6, "exception.parser"),
	SERVICE(7, "exception.service"),
	STATUS(8, "exception.status"),
	CREDENTIAL(9, "exception.credential"), ;

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
