package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.ws.ILoginServiceCaller;
import com.sasd13.androidex.ws.rest.LogInTask;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInService implements IHttpCallback {

    private static final int TASKTYPE_LOGIN = 0;
    private static final int TASKTYPE_READ = 1;

    private ILoginServiceCaller<Teacher> serviceCaller;
    private LogInTask logInTask;
    private ReadTask<Teacher> readTask;
    private int taskType;
    private String number;

    public LogInService(ILoginServiceCaller<Teacher> serviceCaller) {
        this.serviceCaller = serviceCaller;
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
                serviceCaller.onLoad();
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
            ServiceCallerUtils.handleErrors(serviceCaller, logInTask.getResponseErrors());
        } else {
            readTeacher();
        }
    }

    private void readTeacher() {
        taskType = TASKTYPE_READ;
        readTask = new ReadTask<>(WSResources.URL_WS_TEACHERS, this, Teacher.class);

        readTask.putParameter(EnumParameter.NUMBER.getName(), new String[]{number});
        readTask.execute();
    }

    private void onReadTaskSucceeded() {
        if (!readTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, readTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onLogInSucceeded(readTask.getResults().get(0));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_no_data);
            }
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
