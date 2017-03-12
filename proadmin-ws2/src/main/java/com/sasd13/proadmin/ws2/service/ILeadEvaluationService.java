package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;

public interface ILeadEvaluationService {

	void create(LeadEvaluation leadEvaluation);

	void update(ILeadEvaluationUpdateWrapper updateWrapper);

	void delete(LeadEvaluation leadEvaluation);

	List<LeadEvaluation> read(Map<String, String[]> parameters);
}
