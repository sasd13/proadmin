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
import com.sasd13.proadmin.backend.util.adapter.itf.IndividualEvaluationITFAdapter;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class IndividualEvaluationService implements IIndividualEvaluationService {

	@Autowired
	private IIndividualEvaluationDAO individualEvaluationDAO;

	private IndividualEvaluationITFAdapter adapter;

	public IndividualEvaluationService() {
		adapter = new IndividualEvaluationITFAdapter();
	}

	@Override
	public void create(List<IndividualEvaluationBean> individualEvaluationBeans) {
		List<IndividualEvaluation> individualEvaluations = adapter.adaptI2M(individualEvaluationBeans);

		individualEvaluationDAO.create(individualEvaluations);
	}

	@Override
	public void update(List<IndividualEvaluationBean> individualEvaluationBeans) {
		List<IndividualEvaluation> individualEvaluations = adapter.adaptI2M(individualEvaluationBeans);

		individualEvaluationDAO.update(individualEvaluations);
	}

	@Override
	public void delete(List<IndividualEvaluationBean> individualEvaluationBeans) {
		List<IndividualEvaluation> individualEvaluations = adapter.adaptI2M(individualEvaluationBeans);

		individualEvaluationDAO.delete(individualEvaluations);
	}

	@Override
	public List<IndividualEvaluationBean> read(Map<String, Object> criterias) {
		List<IndividualEvaluation> individualEvaluations = individualEvaluationDAO.read(criterias);

		return adapter.adaptM2I(individualEvaluations);
	}
}
