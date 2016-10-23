package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.LogInTask;
import com.sasd13.androidex.net.ws.rest.task.ParameterizedReadTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.LogInActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.EnumWSCodeRes;
import com.sasd13.proadmin.util.ws.EnumWSCode;
import com.sasd13.proadmin.ws.WSInformation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInHandler implements IWSPromise {

    private static final int TASKTYPE_LOGIN = 0;
    private static final int TASKTYPE_READ = 1;

    private LogInActivity logInActivity;
    private String number;
    private LogInTask logInTask;
    private ParameterizedReadTask<Teacher> parameterizedReadTaskTeacher;
    private int taskType;
    
    public LogInHandler(LogInActivity logInActivity) {
        this.logInActivity = logInActivity;
    }

    public void logIn(String number, String password) {
        taskType = TASKTYPE_LOGIN;
        this.number = number;
        logInTask = new LogInTask(WSInformation.URL_AAA_LOGIN, number, password, this);

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
        EnumWSCode wsCode = EnumWSCode.find(logInTask.getWSReponseCode());

        if (wsCode.isError()) {
            logInActivity.onError(EnumWSCodeRes.find(wsCode).getStringRes());
        } else {
            taskType = TASKTYPE_READ;

            Map<String, String[]> parameters = new HashMap<>();
            parameters.put(EnumParameter.NUMBER.getName(), new String[]{ number });

            parameterizedReadTaskTeacher = new ParameterizedReadTask<>(Teacher.class, WSInformation.URL_WS_TEACHERS, parameters, this);
            parameterizedReadTaskTeacher.execute();
        }
    }

    private void onReadTaskTeacherSucceeded() {
        try {
            Teacher teacher = parameterizedReadTaskTeacher.getResults().get(0);

            Cache.keep(logInActivity, teacher);
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
