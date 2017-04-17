package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

public interface IIndividualEvaluationDAO extends IReader<IndividualEvaluation> {

	String TABLE = "individualevaluations";
	String COLUMN_MARK = "_mark";
	String COLUMN_REPORT = "_report";
	String COLUMN_STUDENT = "_student";

	long create(IndividualEvaluation individualEvaluation);

	void update(IndividualEvaluationUpdateWrapper updateWrapper);

	void delete(IndividualEvaluation individualEvaluation);
}
