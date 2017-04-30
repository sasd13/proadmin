package com.sasd13.proadmin.android.service.v2.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.service.v2.IRunningService;
import com.sasd13.proadmin.android.util.adapter.bean2itf.v2.RunningAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v2.RunningAdapterI2B;
import com.sasd13.proadmin.itf.bean.running.RunningBean;
import com.sasd13.proadmin.itf.bean.running.RunningRequestBean;
import com.sasd13.proadmin.itf.bean.running.RunningResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningService implements IRunningService {

    @Override
    public ServiceResult<List<Running>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_BACKEND_RUNNINGS, RunningResponseBean.class);

        promise.setParameters(parameters);

        RunningResponseBean responseBean = (RunningResponseBean) promise.execute();
        List<Running> list = new ArrayList<>();

        if (promise.isSuccess()) {
            RunningAdapterI2B adapter = new RunningAdapterI2B();

            for (RunningBean runningBean : responseBean.getData()) {
                list.add(adapter.adapt(runningBean));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                promise.isSuccess() ? list : Collections.<Running>emptyList()
        );
    }

    @Override
    public ServiceResult<Void> create(Running running) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_RUNNINGS);

        RunningRequestBean requestBean = new RunningRequestBean();
        List<RunningBean> list = new ArrayList<>();

        list.add(new RunningAdapterB2I().adapt(running));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Running> runnings) {
        Promise promise = new Promise("DELETE", Resources.URL_BACKEND_RUNNINGS);

        RunningRequestBean requestBean = new RunningRequestBean();
        List<RunningBean> list = new ArrayList<>();
        RunningAdapterB2I adapter = new RunningAdapterB2I();

        for (Running running : runnings) {
            list.add(adapter.adapt(running));
        }

        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
