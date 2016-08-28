package com.sasd13.proadmin.bean;

public enum EnumAcademicLevel {
	L1("L1"), 
	L2("L2"), 
	L3("L3"), 
	L4("L4"), 
	L5("L5"),
	;

	private String code;

	private EnumAcademicLevel(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static EnumAcademicLevel find(String code) {
		for (EnumAcademicLevel academicLevel : values()) {
			if (academicLevel.code.equalsIgnoreCase(code)) {
				return academicLevel;
			}
		}

		return null;
	}
}
