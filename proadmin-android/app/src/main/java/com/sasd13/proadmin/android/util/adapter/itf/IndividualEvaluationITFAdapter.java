package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.IndividualEvaluation;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.IndividualEvaluationAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.IndividualEvaluationAdapterM2I;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class IndividualEvaluationITFAdapter extends ITFAdapter<IndividualEvaluation, IndividualEvaluationBean> {

    public IndividualEvaluationITFAdapter() {
        super(new IndividualEvaluationAdapterI2M(), new IndividualEvaluationAdapterM2I());
    }
}
