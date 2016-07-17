package com.sasd13.proadmin.util.code.ws;

public enum EnumWSCode {
	UNKNOWN("Unknown code", 0),
	LOGIN_ERROR_TEACHER_NUMBER("Error teacher number", -1),
	LOGIN_ERROR_TEACHER_PASSWORD("Error teacher password", -2);
	
	private String label;
	private int value;
	
	private EnumWSCode(String label, int value) {
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getValue() {
		return value;
	}
	
	public static EnumWSCode find(int value) {
		EnumWSCode code = UNKNOWN;
		
		for (EnumWSCode bufferedCode : values()) {
			if (value == bufferedCode.getValue()) {
				code = bufferedCode;
			}
		}
		
		return code;
	}
}
