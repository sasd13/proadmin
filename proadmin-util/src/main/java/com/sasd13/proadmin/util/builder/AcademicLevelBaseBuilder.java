package com.sasd13.proadmin.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.AcademicLevel;

public class AcademicLevelBaseBuilder implements IBuilder<AcademicLevel> {

	private String code;

	public AcademicLevelBaseBuilder(String code) {
		this.code = code;
	}

	@Override
	public AcademicLevel build() {
		AcademicLevel academicLevel = new AcademicLevel();
		academicLevel.setCode(code);

		return academicLevel;
	}
}
