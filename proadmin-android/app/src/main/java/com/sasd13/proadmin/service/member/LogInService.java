package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.ws.rest.LogInTask;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.service.ILoginServiceCaller;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.ws.WSConstants;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

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
        logInTask = new LogInTask(WSResources.URL_AAA_LOGIN, this, number, password);

        logInTask.setTimeout(WSConstants.DEFAULT_TIMEOUT);
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
                onReadTaskTeacherSucceeded();
                break;
        }
    }

    private void handleErrors(List<String> errors) {
        EnumError error = EnumError.find(Integer.parseInt(errors.get(0)));

        serviceCaller.onError(EnumErrorRes.find(error).getStringRes());
    }

    private void onLogInTaskSucceeded() {
        if (!logInTask.getResponseErrors().isEmpty()) {
            handleErrors(logInTask.getResponseErrors());
        } else {
            taskType = TASKTYPE_READ;
            readTask = new ReadTask<>(WSResources.URL_WS_TEACHERS, this, Teacher.class);

            readTask.putParameter(EnumParameter.NUMBER.getName(), new String[]{number});
            readTask.setTimeout(WSConstants.DEFAULT_TIMEOUT);
            readTask.execute();
        }
    }

    private void onReadTaskTeacherSucceeded() {
        if (!readTask.getResponseErrors().isEmpty()) {
            handleErrors(readTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onLogInSucceeded(readTask.getResults().get(0));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}
