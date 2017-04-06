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

    private Promise promiseRead, promiseCreate, promiseDelete;

    @Override
    public ServiceResult<List<Running>> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_RUNNINGS, Running.class);
        }

        promiseRead.setParameters(parameters);

        List<Running> results = (List<Running>) promiseRead.execute();

        return new ServiceResult<>(
                promiseRead.isSuccess(),
                promiseRead.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Running running) {
        if (promiseCreate == null) {
            promiseCreate = new Promise("POST", WSResources.URL_WS_RUNNINGS);
        }

        promiseCreate.execute(running);

        return new ServiceResult<>(
                promiseCreate.isSuccess(),
                promiseCreate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(Running[] runnings) {
        if (promiseDelete == null) {
            promiseDelete = new Promise("DELETE", WSResources.URL_WS_RUNNINGS);
        }

        promiseDelete.execute(runnings);

        return new ServiceResult<>(
                promiseDelete.isSuccess(),
                promiseDelete.getResponseCode(),
                null
        );
    }
}