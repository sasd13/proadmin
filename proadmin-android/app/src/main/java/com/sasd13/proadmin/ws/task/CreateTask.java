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
public class CreateTask<T> extends AsyncTask<T, Integer, List<Long>> {

    private static final int TIMEOUT = 60000;

    private Context context;
    private WebServiceClient<T> service;
    private List<Long> results;
    private TaskPlanner taskPlanner;

    public CreateTask(Context context, Class<T> mClass) {
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

    public List<Long> getContent() {
        return results;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
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
        doInTaskError();
    }

    @Override
    protected void onPostExecute(List<Long> longs) {
        super.onPostExecute(longs);

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
