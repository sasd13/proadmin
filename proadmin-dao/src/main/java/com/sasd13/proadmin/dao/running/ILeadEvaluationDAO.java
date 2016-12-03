package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public interface ILeadEvaluationDAO extends ISession<LeadEvaluation> {

	String TABLE = "leadevaluations";
	String COLUMN_PLANNINGMARK = "_planningmark";
	String COLUMN_PLANNINGCOMMENT = "_planningcomment";
	String COLUMN_COMMUNICATIONMARK = "_communicationmark";
	String COLUMN_COMMUNICATIONCOMMENT = "_communicationcomment";
	String COLUMN_REPORT_CODE = "_report_code";
	String COLUMN_STUDENT_CODE = "_student_code";
}
