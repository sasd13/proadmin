package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.model.IndividualEvaluation;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.itf.IndividualEvaluationITFAdapter;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationRequestBean;

import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class IndividualEvaluationService implements IIndividualEvaluationService {

    private static final String URL_WS2_INDIVIDUALEVALUATIONS = AppProperties.getProperty(Names.URL_WS2_INDIVIDUALEVALUATIONS);

    private IndividualEvaluationITFAdapter adapter;

    public IndividualEvaluationService() {
        adapter = new IndividualEvaluationITFAdapter();
    }

    @Override
    public ServiceResult<Void> create(List<IndividualEvaluation> individualEvaluations) {
        Promise promise = new Promise("POST", URL_WS2_INDIVIDUALEVALUATIONS + "/create");
        IndividualEvaluationRequestBean requestBean = new IndividualEvaluationRequestBean();

        requestBean.setData(adapter.adaptM2I(individualEvaluations));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(List<IndividualEvaluation> individualEvaluations) {
        Promise promise = new Promise("POST", URL_WS2_INDIVIDUALEVALUATIONS + "/update");
        IndividualEvaluationRequestBean requestBean = new IndividualEvaluationRequestBean();

        requestBean.setData(adapter.adaptM2I(individualEvaluations));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
