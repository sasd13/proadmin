package com.sasd13.proadmin.util.wrapper.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;

public interface IAcademicLevelUpdateWrapper extends IUpdateWrapper<AcademicLevel> {

	String getCode();

	void setCode(String code);
}
