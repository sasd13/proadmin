package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.LeadEvaluation;
import com.sasd13.proadmin.backend.dao.dto.LeadEvaluationDTO;

public interface ILeadEvaluationDAO {

	LeadEvaluationDTO create(LeadEvaluation leadEvaluation);

	void update(LeadEvaluation leadEvaluation);

	void delete(LeadEvaluation leadEvaluation);

	List<LeadEvaluationDTO> read(Map<String, String[]> parameters);
}
