package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.Report;

public interface ReportDAO extends IEntityDAO<Report> {
	
	String TABLE = "reports";
	
	String COLUMN_ID = "report_id";
	String COLUMN_DATEMEETING = "report_datemeeting";
	String COLUMN_WEEK = "report_week";
	String COLUMN_TEAMCOMMENT = "report_teamcomment";
	String COLUMN_TEAM_ID = "report_team_id";
}
