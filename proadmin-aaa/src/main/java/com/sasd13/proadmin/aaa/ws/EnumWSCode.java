package com.sasd13.proadmin.aaa.ws;

import com.sasd13.javaex.net.IHttpCodifiable;

public enum EnumWSCode implements IHttpCodifiable {
	UNKNOWN(0, "Unknown"), 
	OK(100, "Ok"), 
	ERROR_LOGIN_TEACHER(-100, "Error login teacher"),
	;

	private int code;
	private String label;

	private EnumWSCode(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public boolean isError() {
		return code <= 0;
	}

	public static EnumWSCode find(int value) {
		EnumWSCode code = UNKNOWN;

		for (EnumWSCode bufferedCode : values()) {
			if (value == bufferedCode.code) {
				code = bufferedCode;
			}
		}

		return code;
	}
}
