package com.sasd13.proadmin.ws2.db.dao;

import com.sasd13.javaex.dao.hibernate.ISession;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public interface ILeadEvaluationDAO extends ISession<LeadEvaluation> {

	String TABLE = "leadevaluations";
	String COLUMN_PLANNINGMARK = "_planningmark";
	String COLUMN_PLANNINGCOMMENT = "_planningcomment";
	String COLUMN_COMMUNICATIONMARK = "_communicationmark";
	String COLUMN_COMMUNICATIONCOMMENT = "_communicationcomment";
	String COLUMN_REPORT = "_report";
	String COLUMN_STUDENT = "_student";
}
