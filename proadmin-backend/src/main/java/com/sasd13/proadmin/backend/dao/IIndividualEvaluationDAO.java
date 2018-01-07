package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.model.IndividualEvaluation;

public interface IIndividualEvaluationDAO {

	List<IndividualEvaluation> create(List<IndividualEvaluation> individualEvaluations);

	void update(List<IndividualEvaluation> individualEvaluations);

	void delete(List<IndividualEvaluation> individualEvaluations);

	List<IndividualEvaluation> read(Map<String, Object> criterias);
}
