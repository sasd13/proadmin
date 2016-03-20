package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.running.Report;

public interface ReportDAO extends IEntityDAO<Report> {
	
	String TABLE = "reports";
	
	String COLUMN_ID = "id";
	String COLUMN_DATEMEETING = "datemeeting";
	String COLUMN_WEEK = "week";
	String COLUMN_TEAMCOMMENT = "teamcomment";
	String COLUMN_RUNNINGTEAM = "runningteam_id";
}
