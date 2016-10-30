package com.sasd13.proadmin.util.ws;

public enum EnumError {
	UNKNOWN(0, "error.unknown"), 
	DATA_PARSING(-1, "error.data_parsing"), 
	DATA_VALIDATING(-2, "error.data_validating"), 
	SERVICE(-100, "error.service"),
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
