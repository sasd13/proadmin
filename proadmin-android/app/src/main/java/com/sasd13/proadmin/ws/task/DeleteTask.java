package com.sasd13.proadmin.ws.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class DeleteTask<T> extends AsyncTask<Long, Integer, Void> {

    private static final int TIMEOUT = 60000;

    private Context context;
    private WebServiceClient<T> service;
    private TaskPlanner taskPlanner;

    public DeleteTask(Context context, Class<T> mClass) {
        this.context = context;
        service = new WebServiceClient<>(mClass, TIMEOUT);
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, TIMEOUT - 100);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
    }

    @Override
    protected Void doInBackground(Long... ids) {
        if (!isCancelled()) {
            for (long id : ids) {
                service.delete(id);
            }
        }

        return null;
    }

    @Override
    protected void onCancelled(Void aVoid) {
        doInTaskError();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        taskPlanner.stop();

        if (service.getStatusCode() == WebServiceClient.STATUS_OK) {
            doInTaskCompleted();
        } else {
            doInTaskError();
        }
    }

    protected void doInTaskCompleted() {
        Toast.makeText(context, context.getResources().getString(R.string.message_deleted), Toast.LENGTH_SHORT).show();
    }

    protected void doInTaskError() {
        Toast.makeText(context, "La requête n'a pas abouti", Toast.LENGTH_SHORT).show();
    }
}
