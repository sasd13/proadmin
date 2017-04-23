package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.LeadEvaluation;
import com.sasd13.proadmin.ws.bean.update.LeadEvaluationUpdate;

public interface ILeadEvaluationService {

	long create(LeadEvaluation leadEvaluation);

	void update(LeadEvaluationUpdate leadEvaluationUpdate);

	void delete(LeadEvaluation leadEvaluation);

	List<LeadEvaluation> read(Map<String, String[]> parameters);

	List<LeadEvaluation> readAll();
}
