package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

public interface IIndividualEvaluationService {

	void create(List<IndividualEvaluationBean> individualEvaluationBeans);

	void update(List<IndividualEvaluationBean> individualEvaluationBeans);

	void delete(List<IndividualEvaluationBean> individualEvaluationBeans);

	List<IndividualEvaluationBean> read(Map<String, Object> criterias);
}
