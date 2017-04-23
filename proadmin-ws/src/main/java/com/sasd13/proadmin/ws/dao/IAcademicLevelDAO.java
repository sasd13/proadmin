package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.level.IAcademicLevel;

public interface IAcademicLevelDAO extends IReader<IAcademicLevel> {

	String TABLE = "academicLevels";
	String COLUMN_CODE = "_code";
}
