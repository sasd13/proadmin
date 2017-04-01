package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningService implements IRunningService {

    private Promise promiseRead, promiseCreate, promiseDelete;

    @Override
    public List<Running> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_RUNNINGS, Running.class);
        }

        promiseRead.setParameters(parameters);

        return (List<Running>) promiseRead.execute();
    }

    @Override
    public void create(Running running) {
        if (promiseCreate == null) {
            promiseCreate = new Promise("POST", WSResources.URL_WS_RUNNINGS);
        }

        promiseCreate.execute(running);
    }

    @Override
    public void delete(Running running) {
        if (promiseDelete == null) {
            promiseDelete = new Promise("DELETE", WSResources.URL_WS_RUNNINGS);
        }

        promiseDelete.execute(running);
    }
}
