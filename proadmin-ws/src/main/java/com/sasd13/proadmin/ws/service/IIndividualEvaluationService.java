package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

public interface IIndividualEvaluationService {

	long create(IndividualEvaluation individualEvaluation);

	void update(IndividualEvaluationUpdateWrapper updateWrapper);

	void delete(IndividualEvaluation individualEvaluation);

	List<IndividualEvaluation> read(Map<String, String[]> parameters);

	List<IndividualEvaluation> readAll();
}
