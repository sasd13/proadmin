package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.update.IndividualEvaluationUpdate;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.ws.dao.jdbc.IndividualEvaluationDeepReader;
import com.sasd13.proadmin.ws.service.IIndividualEvaluationService;

public class IndividualEvaluationService implements IIndividualEvaluationService {

	private IIndividualEvaluationDAO individualEvaluationDAO;
	private IndividualEvaluationDeepReader individualEvaluationDeepReader;

	public IndividualEvaluationService(DAO dao) {
		individualEvaluationDAO = (IIndividualEvaluationDAO) dao.getSession(IIndividualEvaluationDAO.class);
		individualEvaluationDeepReader = (IndividualEvaluationDeepReader) dao.getDeepReader(IndividualEvaluationDeepReader.class);
	}

	@Override
	public void create(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.create(individualEvaluations);
	}

	@Override
	public void update(List<IndividualEvaluationUpdate> individualEvaluationUpdates) {
		individualEvaluationDAO.update(individualEvaluationUpdates);
	}

	@Override
	public void delete(List<IndividualEvaluation> individualEvaluations) {
		individualEvaluationDAO.delete(individualEvaluations);
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> criterias) {
		return individualEvaluationDeepReader.read(criterias);
	}
}
