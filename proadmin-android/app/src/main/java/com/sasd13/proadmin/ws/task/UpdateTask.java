package com.sasd13.proadmin.ws.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class UpdateTask<T> extends AsyncTask<T, Integer, Void> {

    private static final int TIMEOUT = 60000;

    private Context context;
    private WebServiceClient<T> service;
    private TaskPlanner taskPlanner;

    public UpdateTask(Context context, Class<T> mClass) {
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
    protected Void doInBackground(T... ts) {
        if (!isCancelled()) {
            if (ts.length == 1) {
                service.put(ts[0]);
            } else if (ts.length > 1) {
                service.putAll(ts);
            }
        }

        return null;
    }

    @Override
    protected void onCancelled(Void aVoid) {
        showTaskError();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        taskPlanner.stop();

        if (service.getStatusCode() == WebServiceClient.STATUS_OK) {
            Toast.makeText(context, context.getResources().getString(R.string.message_updated), Toast.LENGTH_SHORT).show();
        } else {
            showTaskError();
        }
    }

    private void showTaskError() {
        CustomDialog.showOkDialog(
                context,
                context.getResources().getString(R.string.title_error),
                "La requête n'a pas abouti"
        );
    }
}
