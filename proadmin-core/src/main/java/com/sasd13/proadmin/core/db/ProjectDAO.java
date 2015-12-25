package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.project.Project;

public interface ProjectDAO extends IEntityDAO<Project> {
	
	String TABLE = "projects";
	
	String COLUMN_ID = "project_id";
	String COLUMN_CODE = "project_code";
	String COLUMN_ACADEMICLEVEL = "project_academiclevel";
	String COLUMN_TITLE = "project_title";
	String COLUMN_DESCRIPTION = "project_description";
}
