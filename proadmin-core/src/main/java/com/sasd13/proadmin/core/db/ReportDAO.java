package com.sasd13.proadmin.core.db;

import java.util.List;

import com.sasd13.proadmin.core.bean.running.Report;

public interface ReportDAO extends EntityDAO<Report> {
	
	String REPORT_TABLE_NAME = "reports";
	
	String REPORT_ID = "report_id";
	String REPORT_DATEMEETING = "report_datemeeting";
	String REPORT_WEEKNUMBER = "report_weeknumber";
	String REPORT_TEAMCOMMENT = "report_teamcomment";
	String TEAMS_TEAM_ID = "teams_team_id";
	
	List<Report> selectByTeam(long teamId);
}
