package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

public interface ILeadEvaluationService {

	long create(LeadEvaluation leadEvaluation);

	void update(LeadEvaluationUpdateWrapper updateWrapper);

	void delete(LeadEvaluation leadEvaluation);

	List<LeadEvaluation> read(Map<String, String[]> parameters);

	List<LeadEvaluation> readAll();
}
