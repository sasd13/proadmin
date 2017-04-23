package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.LeadEvaluation;

public interface ILeadEvaluationService {

	void create(LeadEvaluation leadEvaluation);

	void update(LeadEvaluation leadEvaluation);

	void delete(LeadEvaluation leadEvaluation);

	List<LeadEvaluation> read(Map<String, String[]> parameters);
}