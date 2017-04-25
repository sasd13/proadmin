package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.service.ILeadEvaluationService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.WSResources;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdate;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationService implements ILeadEvaluationService {

    @Override
    public ServiceResult<Void> create(LeadEvaluation leadEvaluation) {
        Promise promise = new Promise("POST", WSResources.URL_WS_LEADEVALUATIONS);

        promise.execute(new LeadEvaluation[]{leadEvaluation});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(LeadEvaluationUpdate leadEvaluationUpdate) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_LEADEVALUATIONS);

        promise.execute(new LeadEvaluationUpdate[]{leadEvaluationUpdate});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }
}
