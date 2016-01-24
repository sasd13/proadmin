package com.sasd13.proadmin.ws.task;

import android.content.Context;
import android.os.AsyncTask;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class CreateTask<T> extends AsyncTask<T, Integer, Long[]> {

    private static final int TIMEOUT = 60000;

    private Context context;
    private WebServiceClient<T> service;
    private Long[] results;
    private TaskPlanner taskPlanner;

    public CreateTask(Context context, Class<T> mClass) {
        this.context = context;
        service = new WebServiceClient<>(mClass, TIMEOUT);
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, TIMEOUT - 100);
    }

    public Long[] getContent() {
        return results;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
    }

    @Override
    protected Long[] doInBackground(T... ts) {
        if (!isCancelled()) {
            if (ts.length > 0) {
                results = new Long[ts.length];

                for (int i = 0; i<results.length; i++) {
                    results[i] = service.post(ts[i]);
                }
            }
        }

        return results;
    }

    @Override
    protected void onCancelled(Long[] longs) {
        doInTaskError();
    }

    @Override
    protected void onPostExecute(Long[] longs) {
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
        CustomDialog.showOkDialog(
                context,
                context.getResources().getString(R.string.title_error),
                "La requête n'a pas abouti"
        );
    }
}
