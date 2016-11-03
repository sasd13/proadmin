package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.Report;

public interface IReportDAO extends IManager<Report>, IReader<Report> {

	String TABLE = "reports";
	String COLUMN_CODE = "code";
	String COLUMN_DATEMEETING = "datemeeting";
	String COLUMN_SESSION = "session";
	String COLUMN_COMMENT = "comment";
	String COLUMN_RUNNINGTEAM_RUNNING_YEAR = "runningteam_running_year";
	String COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE = "runningteam_running_project_code";
	String COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE = "runningteam_running_teacher_code";
	String COLUMN_RUNNINGTEAM_TEAM_CODE = "runningteam_team_code";
	String COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE = "runningteam_academiclevel_code";

	ILeadEvaluationDAO getLeadEvaluationDAO();

	IIndividualEvaluationDAO getIndividualEvaluationDAO();
}
