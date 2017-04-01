package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningService {

    private Promise promiseRead, promiseCreate, promiseDelete;
    private Map<String, String[]> parameters;

    public RunningService() {
        promiseRead = new Promise("GET", WSResources.URL_WS_RUNNINGS, Running.class);
        promiseCreate = new Promise("POST", WSResources.URL_WS_RUNNINGS);
        promiseDelete = new Promise("DELETE", WSResources.URL_WS_RUNNINGS);
        parameters = new HashMap<>();

        promiseRead.setParameters(parameters);
    }

    public void readByTeacherAndProject(ICallback callback, String teacherNumber, String projectCode) {
        parameters.clear();
        parameters.put(EnumParameter.PROJECT.getName(), new String[]{projectCode});
        parameters.put(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void create(ICallback callback, Running running) {
        promiseCreate.registerCallback(callback);
        promiseCreate.execute(running);
    }

    public void delete(ICallback callback, Running running) {
        promiseDelete.registerCallback(callback);
        promiseDelete.execute(running);
    }
}
