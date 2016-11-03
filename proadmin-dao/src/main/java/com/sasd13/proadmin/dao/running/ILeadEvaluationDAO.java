package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public interface ILeadEvaluationDAO extends IManager<LeadEvaluation>, IReader<LeadEvaluation> {

	String TABLE = "leadevaluations";
	String COLUMN_PLANNINGMARK = "planningmark";
	String COLUMN_PLANNINGCOMMENT = "planningcomment";
	String COLUMN_COMMUNICATIONMARK = "communicationmark";
	String COLUMN_COMMUNICATIONCOMMENT = "communicationcomment";
	String COLUMN_REPORT_CODE = "report_code";
	String COLUMN_STUDENT_CODE = "student_code";
}
