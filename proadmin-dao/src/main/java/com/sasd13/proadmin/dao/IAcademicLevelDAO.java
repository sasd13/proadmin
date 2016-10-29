package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.AcademicLevel;

public interface IAcademicLevelDAO extends IEntityDAO<AcademicLevel> {

	String TABLE = "academicLevels";
	String COLUMN_CODE = "code";
}
