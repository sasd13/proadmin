package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

public interface IIndividualEvaluationService {

	void create(IndividualEvaluation individualEvaluation);

	void update(List<IndividualEvaluationUpdateWrapper> updateWrappers);

	void delete(List<IndividualEvaluation> individualEvaluations);

	List<IndividualEvaluation> read(Map<String, String[]> parameters);
}
