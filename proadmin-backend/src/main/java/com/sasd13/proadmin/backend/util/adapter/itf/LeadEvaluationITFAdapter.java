package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.LeadEvaluation;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.LeadEvaluationAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.LeadEvaluationAdapterM2I;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

public class LeadEvaluationITFAdapter extends ITFAdapter<LeadEvaluation, LeadEvaluationBean> {

	public LeadEvaluationITFAdapter() {
		super(new LeadEvaluationAdapterI2M(), new LeadEvaluationAdapterM2I());
	}
}
