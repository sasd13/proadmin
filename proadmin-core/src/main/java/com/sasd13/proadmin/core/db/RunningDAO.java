package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.Running;

public interface RunningDAO extends IEntityDAO<Running> {
	
	String TABLE = "runnings";
	
	String COLUMN_ID = "running_id";
	String COLUMN_YEAR = "running_year";
	String COLUMN_TEACHER_ID = "running_teacher_id";
	String COLUMN_PROJECT_ID = "running_project_id";
}
