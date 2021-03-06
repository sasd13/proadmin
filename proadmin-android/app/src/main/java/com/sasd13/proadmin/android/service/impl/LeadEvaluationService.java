package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.model.LeadEvaluation;
import com.sasd13.proadmin.android.service.ILeadEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.itf.LeadEvaluationITFAdapter;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationRequestBean;

import java.util.Arrays;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationService implements ILeadEvaluationService {

    private static final String URL_WS2_LEADEVALUATIONS = AppProperties.getProperty(Names.URL_WS2_LEADEVALUATIONS);

    private LeadEvaluationITFAdapter adapter;

    public LeadEvaluationService() {
        adapter = new LeadEvaluationITFAdapter();
    }

    @Override
    public ServiceResult<Void> create(LeadEvaluation leadEvaluation) {
        Promise promise = new Promise("POST", URL_WS2_LEADEVALUATIONS + "/create");
        LeadEvaluationRequestBean requestBean = new LeadEvaluationRequestBean();

        requestBean.setData(Arrays.asList(adapter.adaptM2I(leadEvaluation)));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(LeadEvaluation leadEvaluation) {
        Promise promise = new Promise("POST", URL_WS2_LEADEVALUATIONS + "/update");
        LeadEvaluationRequestBean requestBean = new LeadEvaluationRequestBean();

        requestBean.setData(Arrays.asList(adapter.adaptM2I(leadEvaluation)));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
