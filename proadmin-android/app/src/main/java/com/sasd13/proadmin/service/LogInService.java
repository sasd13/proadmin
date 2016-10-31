package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.LogInTask;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.LogInActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.ws.WSConstants;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInService implements IHttpCallback {

    private static final int TASKTYPE_LOGIN = 0;
    private static final int TASKTYPE_READ = 1;

    private LogInActivity logInActivity;
    private String number;
    private LogInTask logInTask;
    private ReadTask<Teacher> readTaskTeacher;
    private int taskType;
    
    public LogInService(LogInActivity logInActivity) {
        this.logInActivity = logInActivity;
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
                logInActivity.onLoad();
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

    private void onLogInTaskSucceeded() {
        if (!logInTask.getResponseErrors().isEmpty()) {
            EnumError error = EnumError.find(Integer.parseInt(logInTask.getResponseErrors().get(0)));

            logInActivity.onError(EnumErrorRes.find(error).getStringRes());
        } else {
            taskType = TASKTYPE_READ;

            readTaskTeacher = new ReadTask<>(WSResources.URL_WS_TEACHERS, this, Teacher.class);
            readTaskTeacher.setTimeout(WSConstants.DEFAULT_TIMEOUT);
            readTaskTeacher.putParameter(EnumParameter.NUMBER.getName(), new String[]{ number });
            readTaskTeacher.execute();
        }
    }

    private void onReadTaskTeacherSucceeded() {
        try {
            Teacher teacher = readTaskTeacher.getResults().get(0);

            logInActivity.onLogInSucceeded(teacher);
        } catch (IndexOutOfBoundsException e) {
            logInActivity.onError(R.string.error_ws_retrieve_data);
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        logInActivity.onError(R.string.error_ws_server_connection);
    }
}
