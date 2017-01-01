package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public interface IIndividualEvaluationDAO extends ISession<IndividualEvaluation> {

	String TABLE = "individualevaluations";
	String COLUMN_MARK = "_mark";
	String COLUMN_REPORT = "_report";
	String COLUMN_STUDENT = "_student";
}
