package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.service.ILeadEvaluationService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationService implements ILeadEvaluationService {

    private Promise promiseCreate, promiseUpdate;

    @Override
    public ServiceResult<Void> create(LeadEvaluation leadEvaluation) {
        if (promiseCreate == null) {
            promiseCreate = new Promise("POST", WSResources.URL_WS_LEADEVALUATIONS);
        }

        promiseCreate.execute(leadEvaluation);

        return new ServiceResult<>(
                promiseCreate.isSuccess(),
                promiseCreate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(LeadEvaluationUpdateWrapper leadEvaluationUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_LEADEVALUATIONS);
        }

        promiseUpdate.execute(leadEvaluationUpdateWrapper);

        return new ServiceResult<>(
                promiseUpdate.isSuccess(),
                promiseUpdate.getResponseCode(),
                null
        );
    }
}
