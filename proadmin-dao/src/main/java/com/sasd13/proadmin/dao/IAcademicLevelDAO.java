package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.AcademicLevel;

public interface IAcademicLevelDAO extends IReader<AcademicLevel> {

	String TABLE = "academicLevels";
	String COLUMN_CODE = "_code";
}
