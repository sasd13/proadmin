package com.sasd13.proadmin.ws.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

import java.util.Map;

/**
 * Created by Samir on 24/12/2015.
 */
public class ParameterizedReadTask<T> extends AsyncTask<Void, Integer, T[]> {

    private static final int TIMEOUT = 60000;

    private Context context;
    private Map<String, String[]> parameters;
    private WebServiceClient<T> service;
    private T[] results;
    private TaskPlanner taskPlanner;

    public ParameterizedReadTask(Context context, Class<T> mClass, Map<String, String[]> parameters) {
        this.context = context;
        this.parameters = parameters;
        service = new WebServiceClient<>(mClass, TIMEOUT);
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, TIMEOUT - 100);
    }

    public T[] getContent() {
        return results;
    }

    public void setDeepReadEnabled(boolean deepReadEnabled) {
        service.setDataRetrieveDeepEnabled(deepReadEnabled);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
    }

    @Override
    protected T[] doInBackground(Void... aVoid) {
        if (!isCancelled()) {
            results = service.get(parameters);
        }

        return results;
    }

    @Override
    protected void onCancelled(T[] ts) {
        doInTaskError();
    }

    @Override
    protected void onPostExecute(T[] ts) {
        super.onPostExecute(ts);

        taskPlanner.stop();

        if (service.getStatusCode() == WebServiceClient.STATUS_OK) {
            doInTaskCompleted();
        } else {
            doInTaskError();
        }
    }

    protected void doInTaskCompleted() {
        //Do nothing
    }

    protected void doInTaskError() {
        Toast.makeText(context, "La requête n'a pas abouti", Toast.LENGTH_SHORT).show();
    }
}
