package com.sasd13.proadmin.util.ws;

public enum EnumWSError {
	UNKNOWN(0, "Unknown"), 
	ERROR_TECHNICAL(-1, "Technical error"), 
	ERROR_PARSING_DATA(-2, "Error parsing data"),
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

		return UNKNOWN;
	}
}
