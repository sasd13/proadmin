package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.ILeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

public interface ILeadEvaluationService {

	long create(ILeadEvaluation iLeadEvaluation);

	void update(LeadEvaluationUpdate updateWrapper);

	void delete(ILeadEvaluation iLeadEvaluation);

	List<ILeadEvaluation> read(Map<String, String[]> parameters);

	List<ILeadEvaluation> readAll();
}
