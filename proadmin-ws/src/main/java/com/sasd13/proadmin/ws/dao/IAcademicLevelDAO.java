package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.AcademicLevel;

public interface IAcademicLevelDAO extends IReader<AcademicLevel> {

	String TABLE = "academicLevels";
	String COLUMN_CODE = "_code";
}
