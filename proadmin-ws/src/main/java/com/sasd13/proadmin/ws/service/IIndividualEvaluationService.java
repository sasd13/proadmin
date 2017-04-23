package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IIndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

public interface IIndividualEvaluationService {

	long create(IIndividualEvaluation iIndividualEvaluation);

	void update(IndividualEvaluationUpdate updateWrapper);

	void delete(IIndividualEvaluation iIndividualEvaluation);

	List<IIndividualEvaluation> read(Map<String, String[]> parameters);

	List<IIndividualEvaluation> readAll();
}
