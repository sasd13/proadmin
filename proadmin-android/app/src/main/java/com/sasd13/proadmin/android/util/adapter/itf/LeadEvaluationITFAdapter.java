package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.LeadEvaluation;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.LeadEvaluationAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.LeadEvaluationAdapterM2I;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class LeadEvaluationITFAdapter extends ITFAdapter<LeadEvaluation, LeadEvaluationBean> {

    public LeadEvaluationITFAdapter() {
        super(new LeadEvaluationAdapterI2M(), new LeadEvaluationAdapterM2I());
    }
}
