package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.IWSPromise;
import com.sasd13.androidex.ws.rest.task.LogInTask;
import com.sasd13.androidex.ws.rest.task.ReadTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.LogInActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSConstants;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInService implements IWSPromise {

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
        if (logInTask.hasResponseWSErrors()) {
            //TODO : manage AAA errors
        } else {
            taskType = TASKTYPE_READ;

            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(EnumParameter.NUMBER.getName(), new String[]{ number });

            readTaskTeacher = new ReadTask<>(WSResources.URL_WS_TEACHERS, this, Teacher.class);
            readTaskTeacher.execute();
        }
    }

    private void onReadTaskTeacherSucceeded() {
        try {
            Teacher teacher = readTaskTeacher.getResults().get(0);

            logInActivity.onLogInSucceeded(teacher);
        } catch (IndexOutOfBoundsException e) {
            logInActivity.onError(R.string.ws_error_data_retrieval_error);
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        logInActivity.onError(R.string.ws_error_server_connection_failed);
    }
}
