package com.sasd13.proadmin.ws.rest;

import android.content.Context;

import com.sasd13.androidex.ws.rest.LogInTask;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.ws.ILoginWebService;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.ws.wrapper.member.TeacherReadWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInRESTCallback extends RESTCallback {

    private static final int TASKTYPE_LOGIN = 0;
    private static final int TASKTYPE_READ = 1;

    private LogInTask logInTask;
    private ReadTask<Teacher> readTask;
    private int taskType;
    private String number;
    private Map<String, String[]> parameters;

    public LogInRESTCallback(Context context, ILoginWebService<Teacher> webService) {
        super(context, null, webService);

        parameters = new HashMap<>();
    }

    public void logIn(String number, String password) {
        taskType = TASKTYPE_LOGIN;
        this.number = number;
        logInTask = new LogInTask(WSResources.URL_AAA_LOGIN, this);

        logInTask.setCredential(number, password);
        logInTask.execute();
    }

    @Override
    public void onLoad() {
        switch (taskType) {
            case TASKTYPE_LOGIN:
                webService.onPreExecute();
                break;
        }
    }

    @Override
    public void onSuccess() {
        switch (taskType) {
            case TASKTYPE_LOGIN:
                onLogInTaskSucceeded();
                break;
            case TASKTYPE_READ:
                onReadTaskSucceeded();
                break;
        }
    }

    private void onLogInTaskSucceeded() {
        if (!logInTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(context, webService, logInTask.getResponseErrors());
        } else {
            readTeacher();
        }
    }

    private void readTeacher() {
        taskType = TASKTYPE_READ;
        readTask = new ReadTask<>(WSResources.URL_WS_TEACHERS, this, Teacher.class);

        parameters.clear();
        parameters.put(EnumParameter.NUMBER.getName(), new String[]{number});
        readTask.execute();
    }

    private void onReadTaskSucceeded() {
        if (!readTask.getResponseErrors().isEmpty()) {
            WebServiceUtils.handleErrors(context, webService, readTask.getResponseErrors());
        } else {
            try {
                ((ILoginWebService) webService).onLogIn(new TeacherReadWrapper(readTask.get().get(0)));
            } catch (InterruptedException e) {
                webService.onError(context.getResources().getString(R.string.error_ws_exception_interrupted));
            } catch (ExecutionException e) {
                webService.onError(context.getResources().getString(R.string.error_ws_exception_execution));
            } catch (IndexOutOfBoundsException e) {
                webService.onError(context.getResources().getString(R.string.error_no_data));
            }
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        webService.onError(context.getResources().getString(R.string.error_ws_server_connection));
    }
}
