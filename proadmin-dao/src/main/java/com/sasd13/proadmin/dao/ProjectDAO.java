package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.project.Project;

public interface ProjectDAO extends IEntityDAO<Project> {

	String TABLE = "projects";

	String COLUMN_ID = "id";
	String COLUMN_ACADEMICLEVEL = "academiclevel";
	String COLUMN_CODE = "code";
	String COLUMN_TITLE = "title";
	String COLUMN_DESCRIPTION = "description";
}
