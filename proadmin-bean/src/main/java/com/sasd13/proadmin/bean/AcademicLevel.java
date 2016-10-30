package com.sasd13.proadmin.bean;

public class AcademicLevel {

	private String code;

	public AcademicLevel(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("AcademicLevel [");
		builder.append("code=" + getCode());
		builder.append("]");

		return builder.toString();
	}
}