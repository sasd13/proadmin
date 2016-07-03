package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;

import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.rest.WSClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 24/12/2015.
 */
public class CreateTask<T> extends AsyncTask<T, Integer, List<Long>> {

    protected Promise promise;
    private WSClient<T> service;
    private List<Long> results;
    private TaskPlanner taskPlanner;

    public CreateTask(Promise promise, Class<T> mClass) {
        this.promise = promise;
        service = new WSClient<>(mClass);
        results = new ArrayList<>();
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, WSConstants.TIMEOUT);
    }

    public List<Long> getResults() {
        return results;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
        promise.onLoad();
    }

    @Override
    protected List<Long> doInBackground(T... ts) {
        if (!isCancelled()) {
            for (T t : ts) {
                results.add(service.post(t));
            }
        }

        return results;
    }

    @Override
    protected void onCancelled(List<Long> longs) {
        promise.onFail();
    }

    @Override
    protected void onPostExecute(List<Long> longs) {
        super.onPostExecute(longs);

        taskPlanner.stop();

        if (service.getStatusCode() == WSConstants.STATUS_OK) {
            promise.onSuccess();
        } else {
            promise.onFail();
        }
    }
}
