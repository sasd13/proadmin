package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.LoginActivity;
import com.sasd13.proadmin.ws.rest.LoginWebServiceClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class LoginTask extends AsyncTask<Void, Integer, Long> {

    private static final int TIMEOUT = LoginWebServiceClient.DEFAULT_TIMEOUT;

    private LoginActivity loginActivity;
    private String number, password;
    private LoginWebServiceClient service;
    private Long result;
    private TaskPlanner taskPlanner;

    public LoginTask(LoginActivity loginActivity, String number, String password) {
        this.loginActivity = loginActivity;
        this.number = number;
        this.password = password;
        service = new LoginWebServiceClient(TIMEOUT);
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, TIMEOUT - 100);
    }

    public Long getResult() {
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
        loginActivity.onLoading();
    }

    @Override
    protected Long doInBackground(Void... aVoid) {
        if (!isCancelled()) {
            result = service.logIn(number, password);
        }

        return result;
    }

    @Override
    protected void onCancelled(Long aLong) {
        onTaskFailed();
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);

        taskPlanner.stop();

        if (service.getStatusCode() == LoginWebServiceClient.STATUS_OK) {
            onTaskSucceeded();
        } else {
            onTaskFailed();
        }
    }

    protected void onTaskSucceeded() {
        loginActivity.onLoadSucceeded();
    }

    protected void onTaskFailed() {
        Toast.makeText(loginActivity, "La requête n'a pas abouti", Toast.LENGTH_SHORT).show();

        loginActivity.onLoadFailed();
    }
}
