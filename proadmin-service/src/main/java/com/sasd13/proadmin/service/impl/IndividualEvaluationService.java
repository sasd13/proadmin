package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.dao.IndividualEvaluationDeepReader;
import com.sasd13.proadmin.service.IIndividualEvaluationService;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

public class IndividualEvaluationService implements IIndividualEvaluationService {

	private IIndividualEvaluationDAO session;
	private IndividualEvaluationDeepReader deepReader;

	public IndividualEvaluationService(DAO dao) {
		session = (IIndividualEvaluationDAO) dao.getSession(IIndividualEvaluationDAO.class);
		deepReader = (IndividualEvaluationDeepReader) dao.getDeepReader(IndividualEvaluationDeepReader.class);
	}

	@Override
	public long create(IndividualEvaluation individualEvaluation) {
		return session.create(individualEvaluation);
	}

	@Override
	public void update(IndividualEvaluationUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) {
		session.delete(individualEvaluation);
	}

	@Override
	public List<IndividualEvaluation> read(Map<String, String[]> parameters) {
		return deepReader.read(parameters);
	}

	@Override
	public List<IndividualEvaluation> readAll() {
		return deepReader.readAll();
	}
}
