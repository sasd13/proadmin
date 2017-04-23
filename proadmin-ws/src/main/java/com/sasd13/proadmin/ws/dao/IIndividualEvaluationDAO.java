package com.sasd13.proadmin.ws.dao;

import java.util.List;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.update.IndividualEvaluationUpdate;

public interface IIndividualEvaluationDAO extends IReader<IndividualEvaluation> {

	String TABLE = "individualevaluations";
	String COLUMN_MARK = "_mark";
	String COLUMN_REPORT = "_report";
	String COLUMN_STUDENT = "_student";

	void create(List<IndividualEvaluation> individualEvaluations);

	void update(List<IndividualEvaluationUpdate> individualEvaluationUpdates);

	void delete(List<IndividualEvaluation> individualEvaluations);
}
