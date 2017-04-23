package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.LeadEvaluation;
import com.sasd13.proadmin.ws.bean.update.LeadEvaluationUpdate;
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
	public long create(LeadEvaluation leadEvaluation) {
		return leadEvaluationDAO.create(leadEvaluation);
	}

	@Override
	public void update(LeadEvaluationUpdate leadEvaluationUpdate) {
		leadEvaluationDAO.update(leadEvaluationUpdate);
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) {
		leadEvaluationDAO.delete(leadEvaluation);
	}

	@Override
	public List<LeadEvaluation> read(Map<String, String[]> parameters) {
		return leadEvaluationDeepReader.read(parameters);
	}

	@Override
	public List<LeadEvaluation> readAll() {
		return leadEvaluationDeepReader.readAll();
	}
}
