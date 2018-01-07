package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.ILeadEvaluationDAO;
import com.sasd13.proadmin.backend.model.LeadEvaluation;
import com.sasd13.proadmin.backend.service.ILeadEvaluationService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.LeadEvaluationAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.LeadEvaluationAdapterM2I;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LeadEvaluationService implements ILeadEvaluationService {

	@Autowired
	private ILeadEvaluationDAO leadEvaluationDAO;

	@Override
	public void create(LeadEvaluationBean leadEvaluationBean) {
		LeadEvaluation leadEvaluation = adaptI2M(leadEvaluationBean);

		leadEvaluationDAO.create(leadEvaluation);
	}

	private LeadEvaluation adaptI2M(LeadEvaluationBean leadEvaluationBean) {
		return new LeadEvaluationAdapterI2M().adapt(leadEvaluationBean);
	}

	@Override
	public void update(LeadEvaluationBean leadEvaluationBean) {
		LeadEvaluation leadEvaluation = adaptI2M(leadEvaluationBean);

		leadEvaluationDAO.update(leadEvaluation);
	}

	@Override
	public void delete(LeadEvaluationBean leadEvaluationBean) {
		LeadEvaluation leadEvaluation = adaptI2M(leadEvaluationBean);

		leadEvaluationDAO.delete(leadEvaluation);
	}

	@Override
	public List<LeadEvaluationBean> read(Map<String, Object> criterias) {
		List<LeadEvaluation> leadEvaluations = leadEvaluationDAO.read(criterias);

		return adaptM2I(leadEvaluations);
	}

	private List<LeadEvaluationBean> adaptM2I(List<LeadEvaluation> leadEvaluations) {
		List<LeadEvaluationBean> list = new ArrayList<>();
		LeadEvaluationAdapterM2I adapter = new LeadEvaluationAdapterM2I();

		for (LeadEvaluation leadEvaluation : leadEvaluations) {
			list.add(adapter.adapt(leadEvaluation));
		}

		return list;
	}
}
