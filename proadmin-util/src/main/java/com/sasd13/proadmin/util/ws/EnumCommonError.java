package com.sasd13.proadmin.util.ws;

public enum EnumCommonError {
	ERROR_UNKNOWN(0, "Error unknown"), 
	ERROR_PARSING_DATA(-1, "Error parsing data"), 
	ERROR_VALIDATING_DATA(-2, "Error validating data"), 
	ERROR_SERVICE(-100, "Error service"),
	;

	private int code;
	private String label;

	private EnumCommonError(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static EnumCommonError find(int code) {
		for (EnumCommonError aaaError : values()) {
			if (code == aaaError.code) {
				return aaaError;
			}
		}

		return ERROR_UNKNOWN;
	}
}
