package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public interface IndividualEvaluationDAO extends IEntityDAO<IndividualEvaluation> {
	
	String TABLE = "individualevaluations";
	
	String COLUMN_ID = "id";
	String COLUMN_MARK = "mark";
	String COLUMN_STUDENT_ID = "student_id";
	String COLUMN_REPORT_ID = "report_id";
}
