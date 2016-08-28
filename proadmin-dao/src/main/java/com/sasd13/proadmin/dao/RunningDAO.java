package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.running.Running;

public interface RunningDAO extends IEntityDAO<Running> {

	String TABLE = "runnings";

	String COLUMN_ID = "id";
	String COLUMN_YEAR = "year";
	String COLUMN_TEACHER_ID = "teacher_id";
	String COLUMN_PROJECT_ID = "project_id";
}
