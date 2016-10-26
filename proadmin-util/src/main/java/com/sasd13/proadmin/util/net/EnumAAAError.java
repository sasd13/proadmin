package com.sasd13.proadmin.util.net;

public enum EnumAAAError {
	UNKNOWN(0, "Unknown"), 
	ERROR_SERVICE(-1, "Service error"), 
	ERROR_LOGIN(-100, "Login error : matching username/password"), 
	ERROR_PARSING_CREDENTIAL(-101, "Parsing error : credential"),
	;

	private int code;
	private String label;

	private EnumAAAError(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static EnumAAAError find(int code) {
		for (EnumAAAError aaaError : values()) {
			if (code == aaaError.code) {
				return aaaError;
			}
		}

		return UNKNOWN;
	}
}
