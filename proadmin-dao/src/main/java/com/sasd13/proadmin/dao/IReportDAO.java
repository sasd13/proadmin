package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.running.Report;

public interface IReportDAO extends ISession<Report> {

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

	ILeadEvaluationDAO getLeadEvaluationDAO();

	IIndividualEvaluationDAO getIndividualEvaluationDAO();
}
