package com.sasd13.proadmin.util.wrapper.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;

public class AcademicLevelUpdateWrapper implements IUpdateWrapper<AcademicLevel> {

	private AcademicLevel academicLevel;
	private String code;

	@Override
	public AcademicLevel getWrapped() {
		return academicLevel;
	}

	@Override
	public void setWrapped(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
