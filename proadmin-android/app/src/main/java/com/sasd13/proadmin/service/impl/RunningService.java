package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningService implements IRunningService {

    @Override
    public ServiceResult<List<Running>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", WSResources.URL_WS_RUNNINGS, Running.class);

        promise.setParameters(parameters);

        List<Running> results = (List<Running>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Running running) {
        Promise promise = new Promise("POST", WSResources.URL_WS_RUNNINGS);

        promise.execute(new Running[]{running});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Running> runnings) {
        Promise promise = new Promise("DELETE", WSResources.URL_WS_RUNNINGS);

        promise.execute(runnings);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }
}
