package com.sasd13.proadmin.ws;

import com.sasd13.androidex.ws.rest.promise.Promise;
import com.sasd13.androidex.ws.rest.task.LogInAsyncTask;
import com.sasd13.androidex.ws.rest.task.ReadAsyncTask;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.aaa.EnumAAASession;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInPromise extends Promise {

    public interface Callback extends Promise.Callback {

        void onLoggedIn(Teacher teacher);
    }

    private static final int TASKTYPE_LOGIN = 0;
    private static final int TASKTYPE_READ = 1;

    private LogInAsyncTask logInTask;
    private ReadAsyncTask<Teacher> readTask;
    private int taskType;
    private Map<String, String[]> parameters;

    public LogInPromise(Callback callback) {
        super(callback);

        parameters = new HashMap<>();
    }

    public void logIn(String number, String password) {
        taskType = TASKTYPE_LOGIN;
        logInTask = new LogInAsyncTask(this, WSResources.URL_AAA_LOGIN);

        logInTask.setCredential(number, password);
        logInTask.execute();
    }

    @Override
    public void onPreExecute() {
        switch (taskType) {
            case TASKTYPE_LOGIN:
                super.onPreExecute();
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
        taskType = TASKTYPE_READ;
        readTask = new ReadAsyncTask<>(this, WSResources.URL_WS_TEACHERS, Teacher.class);

        parameters.clear();

        try {
            parameters.put(EnumParameter.NUMBER.getName(), new String[]{logInTask.get().get(EnumAAASession.USERNAME.getName())});
            readTask.setRequestParameters(parameters);

            readTask.execute();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void onReadTaskSucceeded() {
        try {
            ((Callback) callback).onLoggedIn(readTask.get().get(0));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
