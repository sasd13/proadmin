package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.Running;

public interface IRunningDAO extends IManager<Running>, IReader<Running> {

	String TABLE = "runnings";
	String COLUMN_YEAR = "year";
	String COLUMN_PROJECT_CODE = "project_code";
	String COLUMN_TEACHER_CODE = "teacher_code";
}
