package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.ISession;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public interface IIndividualEvaluationDAO extends ISession<IndividualEvaluation> {

	String TABLE = "individualevaluations";
	String COLUMN_MARK = "mark";
	String COLUMN_REPORT_CODE = "report_code";
	String COLUMN_STUDENT_CODE = "student_code";
}
