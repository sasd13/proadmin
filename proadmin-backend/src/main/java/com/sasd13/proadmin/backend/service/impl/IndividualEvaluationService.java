package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IIndividualEvaluationDAO;
import com.sasd13.proadmin.backend.model.IndividualEvaluation;
import com.sasd13.proadmin.backend.service.IIndividualEvaluationService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.IndividualEvaluationAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.IndividualEvaluationAdapterM2I;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class IndividualEvaluationService implements IIndividualEvaluationService {

	@Autowired
	private IIndividualEvaluationDAO individualEvaluationDAO;

	@Override
	public void create(List<IndividualEvaluationBean> individualEvaluationBeans) {
		List<IndividualEvaluation> individualEvaluations = adaptI2M(individualEvaluationBeans);

		individualEvaluationDAO.create(individualEvaluations);
	}

	private List<IndividualEvaluation> adaptI2M(List<IndividualEvaluationBean> individualEvaluationBeans) {
		List<IndividualEvaluation> list = new ArrayList<>();
		IndividualEvaluationAdapterI2M adapter = new IndividualEvaluationAdapterI2M();

		for (IndividualEvaluationBean individualEvaluationBean : individualEvaluationBeans) {
			list.add(adapter.adapt(individualEvaluationBean));
		}

		return list;
	}

	@Override
	public void update(List<IndividualEvaluationBean> individualEvaluationBeans) {
		List<IndividualEvaluation> individualEvaluations = adaptI2M(individualEvaluationBeans);

		individualEvaluationDAO.update(individualEvaluations);
	}

	@Override
	public void delete(List<IndividualEvaluationBean> individualEvaluationBeans) {
		List<IndividualEvaluation> individualEvaluations = adaptI2M(individualEvaluationBeans);

		individualEvaluationDAO.delete(individualEvaluations);
	}

	@Override
	public List<IndividualEvaluationBean> read(Map<String, Object> criterias) {
		List<IndividualEvaluation> individualEvaluations = individualEvaluationDAO.read(criterias);

		return adaptM2I(individualEvaluations);
	}

	private List<IndividualEvaluationBean> adaptM2I(List<IndividualEvaluation> individualEvaluations) {
		List<IndividualEvaluationBean> list = new ArrayList<>();
		IndividualEvaluationAdapterM2I adapter = new IndividualEvaluationAdapterM2I();

		for (IndividualEvaluation individualEvaluation : individualEvaluations) {
			list.add(adapter.adapt(individualEvaluation));
		}

		return list;
	}
}
