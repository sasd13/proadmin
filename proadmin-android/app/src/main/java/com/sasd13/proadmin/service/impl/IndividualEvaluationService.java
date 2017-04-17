package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.service.IIndividualEvaluationService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class IndividualEvaluationService implements IIndividualEvaluationService {

    @Override
    public ServiceResult<Void> create(List<IndividualEvaluation> individualEvaluations) {
        Promise promise = new Promise("POST", WSResources.URL_WS_INDIVIDUALEVALUATIONS);

        promise.execute(individualEvaluations);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(List<IndividualEvaluationUpdateWrapper> individualEvaluationUpdateWrappers) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_INDIVIDUALEVALUATIONS);

        promise.execute(individualEvaluationUpdateWrappers);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }
}
