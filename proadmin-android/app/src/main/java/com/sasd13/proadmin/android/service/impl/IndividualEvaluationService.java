package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.update.IndividualEvaluationUpdate;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class IndividualEvaluationService implements IIndividualEvaluationService {

    @Override
    public ServiceResult<Void> create(List<IndividualEvaluation> individualEvaluations) {
        Promise promise = new Promise("POST", Resources.URL_WS_INDIVIDUALEVALUATIONS);

        promise.execute(individualEvaluations);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(List<IndividualEvaluationUpdate> individualEvaluationUpdates) {
        Promise promise = new Promise("PUT", Resources.URL_WS_INDIVIDUALEVALUATIONS);

        promise.execute(individualEvaluationUpdates);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }
}
