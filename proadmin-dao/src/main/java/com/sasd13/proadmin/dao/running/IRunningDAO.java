package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.running.Running;

public interface IRunningDAO extends IEntityDAO<Running> {

	String TABLE = "runnings";
	String COLUMN_ID = "id";
	String COLUMN_YEAR = "year";
	String COLUMN_TEACHER_ID = "teacher_id";
	String COLUMN_PROJECT_ID = "project_id";
}
