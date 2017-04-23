package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.ILeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

public interface ILeadEvaluationDAO extends IReader<ILeadEvaluation> {

	String TABLE = "leadevaluations";
	String COLUMN_PLANNINGMARK = "_planningmark";
	String COLUMN_PLANNINGCOMMENT = "_planningcomment";
	String COLUMN_COMMUNICATIONMARK = "_communicationmark";
	String COLUMN_COMMUNICATIONCOMMENT = "_communicationcomment";
	String COLUMN_REPORT = "_report";
	String COLUMN_STUDENT = "_student";

	long create(ILeadEvaluation iLeadEvaluation);

	void update(LeadEvaluationUpdateWrapper updateWrapper);

	void delete(ILeadEvaluation iLeadEvaluation);
}
