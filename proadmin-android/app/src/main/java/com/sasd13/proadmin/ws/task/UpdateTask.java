package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;

import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.rest.WSClient;

import java.util.Arrays;

/**
 * Created by Samir on 24/12/2015.
 */
public class UpdateTask<T> extends AsyncTask<T, Integer, Void> {

    private Promise promise;
    private WSClient<T> service;
    private TaskPlanner taskPlanner;

    public UpdateTask(Promise promise, Class<T> mClass) {
        this.promise = promise;
        service = new WSClient<>(mClass);
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, WSConstants.TIMEOUT);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
        promise.onLoad();
    }

    @Override
    protected Void doInBackground(T... ts) {
        if (!isCancelled()) {
            if (ts.length == 1) {
                service.put(ts[0]);
            } else if (ts.length > 1) {
                service.putAll(Arrays.asList(ts));
            }
        }

        return null;
    }

    @Override
    protected void onCancelled(Void aVoid) {
        promise.onFail();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        taskPlanner.stop();

        if (service.getStatusCode() == WSConstants.STATUS_OK) {
            promise.onSuccess();
        } else {
            promise.onFail();
        }
    }
}
