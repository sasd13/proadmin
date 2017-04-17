package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
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
	public long create(IndividualEvaluation individualEvaluation) {
		return individualEvaluationDAO.create(individualEvaluation);
	}

	@Override
	public void update(IndividualEvaluationUpdateWrapper updateWrapper) {
		individualEvaluationDAO.update(updateWrapper);
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) {
		individualEvaluationDAO.delete(individualEvaluation);
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		return individualEvaluationDeepReader.read(parameters);
	}

	@Override
	public List<IndividualEvaluation> readAll() {
		return individualEvaluationDeepReader.readAll();
	}
}
