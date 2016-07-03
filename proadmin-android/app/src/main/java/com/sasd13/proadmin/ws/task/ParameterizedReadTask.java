package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;

import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.ws.WSConstants;
import com.sasd13.proadmin.ws.rest.WSClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Samir on 24/12/2015.
 */
public class ParameterizedReadTask<T> extends AsyncTask<Void, Integer, List<T>> {

    private Promise promise;
    private Map<String, String[]> parameters;
    private WSClient<T> service;
    private List<T> results;
    private TaskPlanner taskPlanner;

    public ParameterizedReadTask(Promise promise, Class<T> mClass, Map<String, String[]> parameters) {
        this.promise = promise;
        this.parameters = parameters;
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
    protected List<T> doInBackground(Void... aVoid) {
        if (!isCancelled()) {
            results.addAll(service.get(parameters));
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
