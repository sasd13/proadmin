package com.sasd13.proadmin.util.code.ws;

public enum LoginWebServiceCode {
	
	UNKNOWN("Unknown code", 0),
	ERROR_TEACHER_NUMBER("Error teacher number", -1),
	ERROR_TEACHER_PASSWORD("Error teacher password", -2);
	
	private String label;
	private int value;
	
	private LoginWebServiceCode(String label, int value) {
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getValue() {
		return value;
	}
	
	public LoginWebServiceCode find(int value) {
		LoginWebServiceCode code = UNKNOWN;
		
		for (LoginWebServiceCode bufferedCode : values()) {
			if (value == bufferedCode.getValue()) {
				code = bufferedCode;
			}
		}
		
		return code;
	}
}
