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
public class ReadTask<T> extends AsyncTask<Long, Integer, List<T>> {

    private Promise promise;
    private WSClient<T> service;
    private List<T> results;
    private TaskPlanner taskPlanner;

    public ReadTask(Promise promise, Class<T> mClass) {
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

    public List<T> getResults() {
        return results;
    }

    public void setDeepReadEnabled(boolean deepReadEnabled) {
        service.setDataRetrieveDeepEnabled(deepReadEnabled);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
        promise.onLoad();
    }

    @Override
    protected List<T> doInBackground(Long... ids) {
        if (!isCancelled()) {
            if (ids.length > 0) {
                for (long id : ids) {
                    results.add(service.get(id));
                }
            } else {
                results.addAll(service.getAll());
            }
        }

        return results;
    }

    @Override
    protected void onCancelled(List<T> ts) {
        promise.onFail();
    }

    @Override
    protected void onPostExecute(List<T> ts) {
        super.onPostExecute(ts);

        taskPlanner.stop();

        if (service.getStatusCode() == WSConstants.STATUS_OK) {
            promise.onSuccess();
        } else {
            promise.onFail();
        }
    }
}
