package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.ILeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.ws.dao.jdbc.LeadEvaluationDeepReader;
import com.sasd13.proadmin.ws.service.ILeadEvaluationService;

public class LeadEvaluationService implements ILeadEvaluationService {

	private ILeadEvaluationDAO leadEvaluationDAO;
	private LeadEvaluationDeepReader leadEvaluationDeepReader;

	public LeadEvaluationService(DAO dao) {
		leadEvaluationDAO = (ILeadEvaluationDAO) dao.getSession(ILeadEvaluationDAO.class);
		leadEvaluationDeepReader = (LeadEvaluationDeepReader) dao.getDeepReader(LeadEvaluationDeepReader.class);
	}

	@Override
	public long create(ILeadEvaluation iLeadEvaluation) {
		return leadEvaluationDAO.create(iLeadEvaluation);
	}

	@Override
	public void update(LeadEvaluationUpdate updateWrapper) {
		leadEvaluationDAO.update(updateWrapper);
	}

	@Override
	public void delete(ILeadEvaluation iLeadEvaluation) {
		leadEvaluationDAO.delete(iLeadEvaluation);
	}

	@Override
	public List<ILeadEvaluation> read(Map<String, String[]> parameters) {
		return leadEvaluationDeepReader.read(parameters);
	}

	@Override
	public List<ILeadEvaluation> readAll() {
		return leadEvaluationDeepReader.readAll();
	}
}
