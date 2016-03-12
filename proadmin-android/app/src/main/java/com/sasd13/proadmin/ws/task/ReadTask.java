package com.sasd13.proadmin.ws.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samir on 24/12/2015.
 */
public class ReadTask<T> extends AsyncTask<Long, Integer, List<T>> {

    private static final int TIMEOUT = 60000;

    private Context context;
    private WebServiceClient<T> service;
    private List<T> results;
    private TaskPlanner taskPlanner;

    public ReadTask(Context context, Class<T> mClass) {
        this.context = context;
        service = new WebServiceClient<>(mClass, TIMEOUT);
        results = new ArrayList<>();
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, TIMEOUT - 100);
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
        doInTaskError();
    }

    @Override
    protected void onPostExecute(List<T> ts) {
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
