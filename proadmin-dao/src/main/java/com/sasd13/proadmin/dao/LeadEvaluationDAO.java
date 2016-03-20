package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public interface LeadEvaluationDAO extends IEntityDAO<LeadEvaluation> {
	
	String TABLE = "leadevaluations";
	
	String COLUMN_ID = "id";
	String COLUMN_PLANNINGMARK = "planningmark";
	String COLUMN_PLANNINGCOMMENT = "planningcomment";
	String COLUMN_COMMUNICATIONMARK = "communicationmark";
	String COLUMN_COMMUNICATIONCOMMENT = "communicationcomment";
	String COLUMN_REPORT_ID = "report_id";
	String COLUMN_STUDENT_ID = "student_id";
}
