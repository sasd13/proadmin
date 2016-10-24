package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public interface ILeadEvaluationDAO extends IEntityDAO<LeadEvaluation> {

	String TABLE = "leadevaluations";
	String COLUMN_ID = "id";
	String COLUMN_PLANNINGMARK = "planningmark";
	String COLUMN_PLANNINGCOMMENT = "planningcomment";
	String COLUMN_COMMUNICATIONMARK = "communicationmark";
	String COLUMN_COMMUNICATIONCOMMENT = "communicationcomment";
	String COLUMN_STUDENT_ID = "student_id";
	String COLUMN_REPORT_ID = "report_id";
}
