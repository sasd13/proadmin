package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.running.Report;

public interface IReportDAO extends ISession<Report> {

	String TABLE = "reports";
	String COLUMN_CODE = "_code";
	String COLUMN_DATEMEETING = "_datemeeting";
	String COLUMN_SESSION = "_session";
	String COLUMN_COMMENT = "_comment";
	String COLUMN_RUNNINGTEAM_RUNNING_YEAR = "_runningteam_running_year";
	String COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE = "_runningteam_running_project_code";
	String COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE = "_runningteam_running_teacher_code";
	String COLUMN_RUNNINGTEAM_TEAM_CODE = "_runningteam_team_code";
	String COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE = "_runningteam_academiclevel_code";

	ILeadEvaluationDAO getLeadEvaluationDAO();

	IIndividualEvaluationDAO getIndividualEvaluationDAO();
}
