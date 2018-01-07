package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.IndividualEvaluation;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.IndividualEvaluationAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.IndividualEvaluationAdapterM2I;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

public class IndividualEvaluationITFAdapter extends ITFAdapter<IndividualEvaluation, IndividualEvaluationBean> {

	public IndividualEvaluationITFAdapter() {
		super(new IndividualEvaluationAdapterI2M(), new IndividualEvaluationAdapterM2I());
	}
}
