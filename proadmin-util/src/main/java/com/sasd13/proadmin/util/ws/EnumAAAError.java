package com.sasd13.proadmin.util.ws;

public enum EnumAAAError {
	ERROR_UNKNOWN(0, "Error unknown"),
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

		return ERROR_UNKNOWN;
	}
}
