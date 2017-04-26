package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.update.LeadEvaluationUpdate;
import com.sasd13.proadmin.android.service.ILeadEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.LeadEvaluationAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.bean2itf.update.LeadEvaluationUpdateAdapterB2I;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationRequestBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationService implements ILeadEvaluationService {

    @Override
    public ServiceResult<Void> create(LeadEvaluation leadEvaluation) {
        Promise promise = new Promise("POST", Resources.URL_WS_LEADEVALUATIONS);

        LeadEvaluationRequestBean requestBean = new LeadEvaluationRequestBean();
        List<LeadEvaluationBean> list = new ArrayList<>();

        list.add(new LeadEvaluationAdapterB2I().adapt(leadEvaluation));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(LeadEvaluationUpdate leadEvaluationUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_WS_LEADEVALUATIONS);

        LeadEvaluationRequestBean requestBean = new LeadEvaluationRequestBean();
        List<LeadEvaluationBean> list = new ArrayList<>();

        list.add(new LeadEvaluationUpdateAdapterB2I().adapt(leadEvaluationUpdate));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
