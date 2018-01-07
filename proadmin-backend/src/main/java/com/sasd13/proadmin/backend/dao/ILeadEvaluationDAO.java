package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.entity.LeadEvaluation;

public interface ILeadEvaluationDAO {

	LeadEvaluation create(LeadEvaluation leadEvaluation);

	void update(LeadEvaluation leadEvaluation);

	void delete(LeadEvaluation leadEvaluation);

	List<LeadEvaluation> read(Map<String, Object> criterias);
}
