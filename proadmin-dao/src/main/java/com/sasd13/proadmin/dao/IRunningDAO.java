package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.running.Running;

public interface IRunningDAO extends ISession<Running> {

	String TABLE = "runnings";
	String COLUMN_YEAR = "_year";
	String COLUMN_PROJECT_CODE = "_project_code";
	String COLUMN_TEACHER_CODE = "_teacher_code";
}