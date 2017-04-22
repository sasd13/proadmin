package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;

public interface IProjectDAO extends IReader<Project> {

	String TABLE = "projects";
	String COLUMN_CODE = "_code";
	String COLUMN_DATECREATION = "_datecreation";
	String COLUMN_TITLE = "_title";
	String COLUMN_DESCRIPTION = "_description";

	long create(Project project);

	void update(ProjectUpdateWrapper updateWrapper);

	void delete(Project project);
}
