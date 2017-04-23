package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.update.ReportUpdate;

public interface IReportDAO extends IReader<Report> {

	String TABLE = "reports";
	String COLUMN_CODE = "_code";
	String COLUMN_DATEMEETING = "_datemeeting";
	String COLUMN_SESSION = "_session";
	String COLUMN_COMMENT = "_comment";
	String COLUMN_YEAR = "_year";
	String COLUMN_PROJECT = "_project";
	String COLUMN_TEACHER = "_teacher";
	String COLUMN_TEAM = "_team";
	String COLUMN_ACADEMICLEVEL = "_academiclevel";

	long create(Report report);

	void update(ReportUpdate reportUpdate);

	void delete(Report report);
}
