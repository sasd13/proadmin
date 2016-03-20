package com.sasd13.proadmin.bean;

public enum AcademicLevel {
	L1("L1"),
	L2("L2"),
	L3("L3"),
	M1("M1"),
	M2("M2"),
	UNKNOWN("UNKNOWN");
	
	private String name;
	
	private AcademicLevel(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static AcademicLevel find(String name) {
		AcademicLevel academicLevel = UNKNOWN;
		
		for (AcademicLevel bufferedAcademicLevel : AcademicLevel.values()) {
			if (bufferedAcademicLevel.getName().equalsIgnoreCase(name)) {
				academicLevel = bufferedAcademicLevel;
			}
		}
		
		return academicLevel;
	}
}
