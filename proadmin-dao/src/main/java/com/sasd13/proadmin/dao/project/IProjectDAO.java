package com.sasd13.proadmin.dao.project;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.project.Project;

public interface IProjectDAO extends IEntityDAO<Project> {

	String TABLE = "projects";
	String COLUMN_CODE = "code";
	String COLUMN_DATECREATION = "datecreation";
	String COLUMN_TITLE = "title";
	String COLUMN_DESCRIPTION = "description";
}
