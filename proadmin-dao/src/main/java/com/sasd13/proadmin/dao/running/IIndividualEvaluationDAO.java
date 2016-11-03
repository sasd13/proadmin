package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public interface IIndividualEvaluationDAO extends IManager<IndividualEvaluation>, IReader<IndividualEvaluation> {

	String TABLE = "individualevaluations";
	String COLUMN_MARK = "mark";
	String COLUMN_REPORT_CODE = "report_code";
	String COLUMN_STUDENT_CODE = "student_code";
}
