package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.running.Report;

public interface IReportDAO extends IEntityDAO<Report> {

	String TABLE = "reports";
	String COLUMN_ID = "id";
	String COLUMN_MEETINGDATE = "meetingdate";
	String COLUMN_SESSIONNUMBER = "sessionnumber";
	String COLUMN_COMMENT = "comment";
	String COLUMN_RUNNINGTEAM = "runningteam_id";

	ILeadEvaluationDAO getLeadEvaluationDAO();

	IIndividualEvaluationDAO getIndividualEvaluationDAO();
}
