package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.ws.bean.LeadEvaluation;
import com.sasd13.proadmin.ws.bean.update.LeadEvaluationUpdate;

public interface ILeadEvaluationDAO extends IReader<LeadEvaluation> {

	String TABLE = "leadevaluations";
	String COLUMN_PLANNINGMARK = "_planningmark";
	String COLUMN_PLANNINGCOMMENT = "_planningcomment";
	String COLUMN_COMMUNICATIONMARK = "_communicationmark";
	String COLUMN_COMMUNICATIONCOMMENT = "_communicationcomment";
	String COLUMN_REPORT = "_report";
	String COLUMN_STUDENT = "_student";

	long create(LeadEvaluation leadEvaluation);

	void update(LeadEvaluationUpdate leadEvaluationUpdate);

	void delete(LeadEvaluation leadEvaluation);
}
