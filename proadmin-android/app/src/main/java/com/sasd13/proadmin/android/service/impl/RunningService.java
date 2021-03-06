package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.service.IRunningService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.itf.RunningITFAdapter;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.running.RunningRequestBean;
import com.sasd13.proadmin.itf.bean.running.RunningResponseBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningService implements IRunningService {

    private static final String URL_WS2_RUNNINGS = AppProperties.getProperty(Names.URL_WS2_RUNNINGS);

    private RunningITFAdapter adapter;

    public RunningService() {
        adapter = new RunningITFAdapter();
    }

    @Override
    public ServiceResult<List<Running>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", URL_WS2_RUNNINGS + "/search", RunningResponseBean.class);
        SearchBean searchBean = new SearchBean();

        searchBean.setCriterias(criterias);

        RunningResponseBean responseBean = (RunningResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        List<Running> list = new ArrayList<>();

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                list = adapter.adaptI2M(responseBean.getData());
            }
        }

        return new ServiceResult<>(
                promise.isSuccess() && errors.isEmpty(),
                promise.getResponseCode(),
                errors,
                list
        );
    }

    @Override
    public ServiceResult<Void> create(Running running) {
        Promise promise = new Promise("POST", URL_WS2_RUNNINGS + "/create");
        RunningRequestBean requestBean = new RunningRequestBean();

        requestBean.setData(Arrays.asList(adapter.adaptM2I(running)));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Running> runnings) {
        Promise promise = new Promise("POST", URL_WS2_RUNNINGS + "/delete");
        RunningRequestBean requestBean = new RunningRequestBean();

        requestBean.setData(adapter.adaptM2I(runnings));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
