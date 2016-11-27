package com.sasd13.proadmin.ws.rest;

import com.sasd13.androidex.ws.rest.callback.RESTCallback;
import com.sasd13.androidex.ws.rest.task.LogInAsyncTask;
import com.sasd13.androidex.ws.rest.task.ReadAsyncTask;
import com.sasd13.javaex.ws.ILoginWebService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInRESTCallback extends RESTCallback {

    private static final int TASKTYPE_LOGIN = 0;
    private static final int TASKTYPE_READ = 1;

    private LogInAsyncTask logInTask;
    private ReadAsyncTask<Teacher> readTask;
    private int taskType;
    private String number;
    private Map<String, String[]> parameters;

    public LogInRESTCallback(ILoginWebService<Teacher> webService) {
        super(webService);

        parameters = new HashMap<>();
    }

    public void logIn(String number, String password) {
        taskType = TASKTYPE_LOGIN;
        this.number = number;
        logInTask = new LogInAsyncTask(WSResources.URL_AAA_LOGIN, this);

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
            webService.onError(logInTask.getResponseErrors());
        } else {
            readTeacher();
        }
    }

    private void readTeacher() {
        taskType = TASKTYPE_READ;
        readTask = new ReadAsyncTask<>(WSResources.URL_WS_TEACHERS, this, Teacher.class);

        parameters.clear();
        parameters.put(EnumParameter.NUMBER.getName(), new String[]{number});
        readTask.setParameters(parameters);

        readTask.execute();
    }

    private void onReadTaskSucceeded() {
        if (!readTask.getResponseErrors().isEmpty()) {
            webService.onError(readTask.getResponseErrors());
        } else {
            try {
                ((ILoginWebService) webService).onLogIn(readTask.get().get(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
