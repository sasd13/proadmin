package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.javaex.dao.hibernate.ISession;
import com.sasd13.proadmin.bean.AcademicLevel;

public interface IAcademicLevelDAO extends ISession<AcademicLevel> {

	String TABLE = "academicLevels";
	String COLUMN_CODE = "_code";
}
