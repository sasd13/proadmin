package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

public interface ILeadEvaluationService {

	void create(LeadEvaluationBean leadEvaluationBean);

	void update(LeadEvaluationBean leadEvaluationBean);

	void delete(LeadEvaluationBean leadEvaluationBean);

	List<LeadEvaluationBean> read(Map<String, Object> criterias);
}
