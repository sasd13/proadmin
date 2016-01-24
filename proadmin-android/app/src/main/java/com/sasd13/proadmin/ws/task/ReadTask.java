package com.sasd13.proadmin.ws.task;

import android.content.Context;
import android.os.AsyncTask;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

import java.lang.reflect.Array;

/**
 * Created by Samir on 24/12/2015.
 */
public class ReadTask<T> extends AsyncTask<Long, Integer, T[]> {

    private static final int TIMEOUT = 60000;

    private Context context;
    private Class<T> mClass;
    private WebServiceClient<T> service;
    private T[] results;
    private TaskPlanner taskPlanner;

    public ReadTask(Context context, Class<T> mClass) {
        this.context = context;
        this.mClass = mClass;
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
    }

    @Override
    protected T[] doInBackground(Long... ids) {
        if (!isCancelled()) {
            if (ids.length > 0) {
                results = (T[]) Array.newInstance(mClass, ids.length);

                for (int i = 0; i<ids.length; i++) {
                    Array.set(results, i, service.get(ids[i]));
                }
            } else {
                results = service.getAll();
            }
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
        CustomDialog.showOkDialog(
                context,
                context.getResources().getString(R.string.title_error),
                "La requête n'a pas abouti"
        );
    }
}
