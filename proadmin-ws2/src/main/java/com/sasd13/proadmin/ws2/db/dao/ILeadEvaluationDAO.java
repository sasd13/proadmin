package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.LeadEvaluationDTO;

public interface ILeadEvaluationDAO {

	LeadEvaluationDTO create(LeadEvaluation leadEvaluation);

	void update(ILeadEvaluationUpdateWrapper updateWrapper);

	void delete(LeadEvaluation leadEvaluation);

	List<LeadEvaluationDTO> read(Map<String, String[]> parameters);
}
