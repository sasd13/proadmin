package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.update.IndividualEvaluationUpdate;

public interface IIndividualEvaluationService {

	void create(List<IndividualEvaluation> individualEvaluations);

	void update(List<IndividualEvaluationUpdate> individualEvaluationUpdates);

	void delete(List<IndividualEvaluation> individualEvaluations);

	List<IndividualEvaluation> read(Map<String, String[]> criterias);
}
