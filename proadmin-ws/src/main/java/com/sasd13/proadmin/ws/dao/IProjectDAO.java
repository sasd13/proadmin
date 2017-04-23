package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.ws.bean.update.ProjectUpdate;

public interface IProjectDAO extends IReader<IProject> {

	String TABLE = "projects";
	String COLUMN_CODE = "_code";
	String COLUMN_DATECREATION = "_datecreation";
	String COLUMN_TITLE = "_title";
	String COLUMN_DESCRIPTION = "_description";

	long create(IProject iProject);

	void update(ProjectUpdate updateWrapper);

	void delete(IProject iProject);
}
