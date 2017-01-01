package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.javaex.dao.hibernate.ISession;
import com.sasd13.proadmin.bean.running.Report;

public interface IReportDAO extends ISession<Report> {

	String TABLE = "reports";
	String COLUMN_CODE = "_code";
	String COLUMN_DATEMEETING = "_datemeeting";
	String COLUMN_SESSION = "_session";
	String COLUMN_COMMENT = "_comment";
	String COLUMN_RUNNINGTEAM = "_runningteam";
}
