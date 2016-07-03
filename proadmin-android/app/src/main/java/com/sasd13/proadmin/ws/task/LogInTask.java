package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;

import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.rest.LoginWSClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class LogInTask extends AsyncTask<Void, Integer, Long> {

    private Promise promise;
    private String number, password;
    private LoginWSClient service;
    private Long result;
    private TaskPlanner taskPlanner;

    public LogInTask(Promise promise, String number, String password) {
        this.promise = promise;
        this.number = number;
        this.password = password;
        service = new LoginWSClient();
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, WSConstants.TIMEOUT);
    }

    public Long getResult() {
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
        promise.onLoad();
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
        promise.onFail();
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);

        taskPlanner.stop();

        if (service.getStatusCode() == WSConstants.STATUS_OK) {
            promise.onSuccess();
        } else {
            promise.onFail();
        }
    }
}
