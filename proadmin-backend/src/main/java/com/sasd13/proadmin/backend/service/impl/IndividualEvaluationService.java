package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.backend.model.IndividualEvaluation;
import com.sasd13.proadmin.backend.service.IIndividualEvaluationService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class IndividualEvaluationService implements IIndividualEvaluationService {

	@Autowired
	private IIndividualEvaluationDAO individualEvaluationDAO;

	@Override
	public void create(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.create(individualEvaluations);
	}

	@Override
	public void update(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.update(individualEvaluations);
	}

	@Override
	public void delete(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.delete(individualEvaluations);
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, Object> criterias) {
		return individualEvaluationDAO.read(criterias);
	}
}
