package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.update.IndividualEvaluationUpdate;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.IndividualEvaluationAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.bean2itf.update.IndividualEvaluationUpdateAdapterB2I;
import com.sasd13.proadmin.itf.RequestBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class IndividualEvaluationService implements IIndividualEvaluationService {

    @Override
    public ServiceResult<Void> create(List<IndividualEvaluation> individualEvaluations) {
        Promise promise = new Promise("POST", Resources.URL_WS_INDIVIDUALEVALUATIONS);

        RequestBean requestBean = new RequestBean();
        List<IndividualEvaluationBean> list = new ArrayList<>();
        IndividualEvaluationAdapterB2I adapter = new IndividualEvaluationAdapterB2I();

        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            list.add(adapter.adapt(individualEvaluation));
        }

        requestBean.setData(list);
        promise.execute(requestBean);

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

        RequestBean requestBean = new RequestBean();
        List<IndividualEvaluationBean> list = new ArrayList<>();
        IndividualEvaluationUpdateAdapterB2I adapter = new IndividualEvaluationUpdateAdapterB2I();

        for (IndividualEvaluationUpdate individualEvaluationUpdate : individualEvaluationUpdates) {
            list.add(adapter.adapt(individualEvaluationUpdate));
        }

        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }
}
