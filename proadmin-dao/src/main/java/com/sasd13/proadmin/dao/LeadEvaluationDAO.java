package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public interface LeadEvaluationDAO extends IEntityDAO<LeadEvaluation> {
	
	String TABLE = "leadevaluations";
	
	String COLUMN_ID = "leadevaluation_id";
	String COLUMN_PLANNINGMARK = "leadevaluation_planningmark";
	String COLUMN_PLANNINGCOMMENT = "leadevaluation_planningcomment";
	String COLUMN_COMMUNICATIONMARK = "leadevaluation_communicationmark";
	String COLUMN_COMMUNICATIONCOMMENT = "leadevaluation_communicationcomment";
	String COLUMN_REPORT_ID = "leadevaluation_report_id";
	String COLUMN_STUDENT_ID = "leadevaluation_student_id";
}
