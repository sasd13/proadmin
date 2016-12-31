package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ManagePromise;
import com.sasd13.androidex.ws.rest.promise.ReadPromise;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class RunningService {

    public interface Callback extends ReadPromise.Callback<Running>, ManagePromise.Callback {
    }

    private ReadPromise<Running> readPromise;
    private ManagePromise<Running> managePromise;

    public RunningService(Callback callback) {
        readPromise = new ReadPromise<>(callback, WSResources.URL_WS_RUNNINGS, Running.class);
        managePromise = new ManagePromise<>(callback, WSResources.URL_WS_RUNNINGS);
    }

    public void readByTeacherAndProject(String teacherNumber, String projectCode) {
        readPromise.clearHeaders();
        readPromise.clearParameters();
        readPromise.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readPromise.putParameters(EnumParameter.PROJECT.getName(), new String[]{projectCode});
        readPromise.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readPromise.read();
    }

    public void create(Running running) {
        managePromise.create(running);
    }

    public void delete(Running running) {
        managePromise.delete(running);
    }
}
