package com.sasd13.proadmin.util.ws;

import com.sasd13.javaex.net.IHttpCodifiable;

public enum EnumWSCode implements IHttpCodifiable {
	UNKNOWN							(0, 	"Unknown"),
	OK								(100, 	"Ok"),
	ERROR_LOGIN_TEACHER_NUMBER		(-100, 	"Error teacher number"),
	ERROR_LOGIN_TEACHER_PASSWORD	(-101, 	"Error teacher password"),
	ERROR_POST						(-200, 	"Error post"),
	ERROR_PUT						(-201, 	"Error put"),
	ERROR_DELETE					(-202, 	"Error delete"),
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
