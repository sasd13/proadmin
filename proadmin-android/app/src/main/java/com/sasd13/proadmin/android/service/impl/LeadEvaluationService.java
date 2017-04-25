package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.update.LeadEvaluationUpdate;
import com.sasd13.proadmin.android.service.ILeadEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationService implements ILeadEvaluationService {

    @Override
    public ServiceResult<Void> create(LeadEvaluation leadEvaluation) {
        Promise promise = new Promise("POST", Resources.URL_WS_LEADEVALUATIONS);

        promise.execute(new LeadEvaluation[]{leadEvaluation});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(LeadEvaluationUpdate leadEvaluationUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_WS_LEADEVALUATIONS);

        promise.execute(new LeadEvaluationUpdate[]{leadEvaluationUpdate});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }
}
