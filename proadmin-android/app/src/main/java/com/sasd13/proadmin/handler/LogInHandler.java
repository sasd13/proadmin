package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.LogInTask;
import com.sasd13.androidex.net.ws.rest.task.ReadTask;
import com.sasd13.proadmin.activities.LogInActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.util.EnumWSCodeRes;
import com.sasd13.proadmin.util.ws.EnumWSCode;
import com.sasd13.proadmin.ws.WSInformation;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInHandler implements IWSPromise {

    private static final int TASKTYPE_LOGIN = 0;
    private static final int TASKTYPE_READ = 1;

    private LogInActivity logInActivity;
    private LogInTask logInTask;
    private ReadTask<Teacher> readTaskTeacher;
    private int taskType;
    
    public LogInHandler(LogInActivity logInActivity) {
        this.logInActivity = logInActivity;
    }

    public void logIn(String number, String password) {
        taskType = TASKTYPE_LOGIN;
        logInTask = new LogInTask(WSInformation.URL_LOGIN, number, password, this);

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
            readTaskTeacher = new ReadTask<>(Teacher.class, WSInformation.URL_TEACHERS, this);

            readTaskTeacher.execute(logInTask.getResult());
        }
    }

    private void onReadTaskTeacherSucceeded() {
        try {
            Teacher teacher = readTaskTeacher.getResults().get(0);

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
