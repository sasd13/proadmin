package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.backend.model.LeadEvaluation;
import com.sasd13.proadmin.backend.service.ILeadEvaluationService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LeadEvaluationService implements ILeadEvaluationService {

	@Autowired
	private ILeadEvaluationDAO leadEvaluationDAO;

	@Override
	public void create(LeadEvaluation leadEvaluation) {
		leadEvaluationDAO.create(leadEvaluation);
	}

	@Override
	public void update(LeadEvaluation leadEvaluation) {
		leadEvaluationDAO.update(leadEvaluation);
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		leadEvaluationDAO.delete(leadEvaluation);
	}

	@Override
	public List<LeadEvaluation> read(Map<String, Object> criterias) {
		return leadEvaluationDAO.read(criterias);
	}
}
