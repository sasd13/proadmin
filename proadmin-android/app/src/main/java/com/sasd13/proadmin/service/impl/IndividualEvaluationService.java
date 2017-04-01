package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.service.IIndividualEvaluationService;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class IndividualEvaluationService implements IIndividualEvaluationService {

    private Promise promiseCreate, promiseUpdate;

    @Override
    public void create(List<IndividualEvaluation> individualEvaluations) {
        if (promiseCreate == null) {
            promiseCreate = new Promise("POST", WSResources.URL_WS_INDIVIDUALEVALUATIONS);
        }

        promiseCreate.execute(individualEvaluations);
    }

    @Override
    public void update(List<IndividualEvaluationUpdateWrapper> individualEvaluationUpdateWrappers) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_INDIVIDUALEVALUATIONS);
        }

        promiseUpdate.execute(individualEvaluationUpdateWrappers);
    }
}
