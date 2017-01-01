package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.javaex.dao.hibernate.ISession;
import com.sasd13.proadmin.bean.project.Project;

public interface IProjectDAO extends ISession<Project> {

	String TABLE = "projects";
	String COLUMN_CODE = "_code";
	String COLUMN_DATECREATION = "_datecreation";
	String COLUMN_TITLE = "_title";
	String COLUMN_DESCRIPTION = "_description";
}
