package com.sasd13.proadmin.bean;

public enum EnumAcademicLevel {
	L1("L1"),
	L2("L2"),
	L3("L3"),
	M1("M1"),
	M2("M2"),
	;
	
	private String name;
	
	private EnumAcademicLevel(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static EnumAcademicLevel find(String name) {
		for (EnumAcademicLevel academicLevel : values()) {
			if (academicLevel.name.equalsIgnoreCase(name)) {
				return academicLevel;
			}
		}
		
		return null;
	}
}
