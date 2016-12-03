package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public interface IIndividualEvaluationDAO extends ISession<IndividualEvaluation> {

	String TABLE = "individualevaluations";
	String COLUMN_MARK = "_mark";
	String COLUMN_REPORT_CODE = "_report_code";
	String COLUMN_STUDENT_CODE = "_student_code";
}
