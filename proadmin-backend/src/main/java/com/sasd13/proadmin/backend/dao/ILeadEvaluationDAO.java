package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.dao.dto.LeadEvaluationDTO;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

public interface ILeadEvaluationDAO {

	LeadEvaluationDTO create(LeadEvaluation leadEvaluation);

	void update(List<LeadEvaluationUpdateWrapper> updateWrappers);

	void delete(List<LeadEvaluation> leadEvaluations);

	List<LeadEvaluationDTO> read(Map<String, String[]> parameters);
}
