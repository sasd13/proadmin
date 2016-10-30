package com.sasd13.proadmin.util.ws;

public enum EnumWSError {
	ERROR_UNKNOWN(0, "Error unknown"),
	;

	private int code;
	private String label;

	private EnumWSError(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static EnumWSError find(int code) {
		for (EnumWSError wsError : values()) {
			if (code == wsError.code) {
				return wsError;
			}
		}

		return ERROR_UNKNOWN;
	}
}
