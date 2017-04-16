package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.dao.LeadEvaluationDeepReader;
import com.sasd13.proadmin.service.ILeadEvaluationService;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

public class LeadEvaluationService implements ILeadEvaluationService {

	private ILeadEvaluationDAO session;
	private LeadEvaluationDeepReader deepReader;

	public LeadEvaluationService(DAO dao) {
		session = (ILeadEvaluationDAO) dao.getSession(ILeadEvaluationDAO.class);
		deepReader = (LeadEvaluationDeepReader) dao.getDeepReader(LeadEvaluationDeepReader.class);
	}

	@Override
	public long create(LeadEvaluation leadEvaluation) {
		return session.create(leadEvaluation);
	}

	@Override
	public void update(LeadEvaluationUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		session.delete(leadEvaluation);
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		return deepReader.read(parameters);
	}

	@Override
	public List<LeadEvaluation> readAll() {
		return deepReader.readAll();
	}
}
