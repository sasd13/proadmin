package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.bean.running.IIndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

public interface IIndividualEvaluationDAO extends IReader<IIndividualEvaluation> {

	String TABLE = "individualevaluations";
	String COLUMN_MARK = "_mark";
	String COLUMN_REPORT = "_report";
	String COLUMN_STUDENT = "_student";

	long create(IIndividualEvaluation iIndividualEvaluation);

	void update(IndividualEvaluationUpdateWrapper updateWrapper);

	void delete(IIndividualEvaluation iIndividualEvaluation);
}
