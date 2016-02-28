package com.sasd13.proadmin.core.db;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;

public interface IndividualEvaluationDAO extends IEntityDAO<IndividualEvaluation> {
	
	String TABLE = "individualevaluations";
	
	String COLUMN_ID = "individualevaluation_id";
	String COLUMN_MARK = "individualevaluation_mark";
	String COLUMN_REPORT_ID = "individualevaluation_report_id";
	String COLUMN_STUDENT_ID = "individualevaluation_student_id";
}
