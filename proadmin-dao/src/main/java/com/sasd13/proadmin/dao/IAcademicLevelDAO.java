package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.AcademicLevel;

public interface IAcademicLevelDAO extends ISession<AcademicLevel> {

	String TABLE = "academicLevels";
	String COLUMN_CODE = "code";
}
