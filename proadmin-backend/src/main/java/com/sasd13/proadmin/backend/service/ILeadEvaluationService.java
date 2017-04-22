package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

public interface ILeadEvaluationService {

	void create(LeadEvaluation leadEvaluation);

	void update(List<LeadEvaluationUpdateWrapper> updateWrappers);

	void delete(List<LeadEvaluation> leadEvaluations);

	List<LeadEvaluation> read(Map<String, String[]> parameters);
}
