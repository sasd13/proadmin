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
import com.sasd13.proadmin.backend.util.adapter.itf.LeadEvaluationITFAdapter;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LeadEvaluationService implements ILeadEvaluationService {

	@Autowired
	private ILeadEvaluationDAO leadEvaluationDAO;

	private LeadEvaluationITFAdapter adapter;

	public LeadEvaluationService() {
		adapter = new LeadEvaluationITFAdapter();
	}

	@Override
	public void create(LeadEvaluationBean leadEvaluationBean) {
		LeadEvaluation leadEvaluation = adapter.adaptI2M(leadEvaluationBean);

		leadEvaluationDAO.create(leadEvaluation);
	}

	@Override
	public void update(LeadEvaluationBean leadEvaluationBean) {
		LeadEvaluation leadEvaluation = adapter.adaptI2M(leadEvaluationBean);

		leadEvaluationDAO.update(leadEvaluation);
	}

	@Override
	public void delete(LeadEvaluationBean leadEvaluationBean) {
		LeadEvaluation leadEvaluation = adapter.adaptI2M(leadEvaluationBean);

		leadEvaluationDAO.delete(leadEvaluation);
	}

	@Override
	public List<LeadEvaluationBean> read(Map<String, Object> criterias) {
		List<LeadEvaluation> leadEvaluations = leadEvaluationDAO.read(criterias);

		return adapter.adaptM2I(leadEvaluations);
	}
}
