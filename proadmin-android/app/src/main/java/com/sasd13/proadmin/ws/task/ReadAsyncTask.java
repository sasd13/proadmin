package com.sasd13.proadmin.ws.task;

import android.app.Activity;
import android.os.AsyncTask;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.IRefreshable;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

import java.lang.reflect.Array;

/**
 * Created by Samir on 24/12/2015.
 */
public class ReadAsyncTask<T> extends AsyncTask<Integer, Integer, T[]> {

    private static final int TIMEOUT = 60000;

    private Activity activity;
    private Class<T> mClass;
    private WebServiceClient<T> service;
    private T[] ts;

    public ReadAsyncTask(Activity activity, Class<T> mClass) {
        this.activity = activity;
        this.mClass = mClass;
        service = new WebServiceClient<>(mClass, TIMEOUT);
    }

    public T[] getContent() {
        return ts;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (activity instanceof IRefreshable) {
            ((IRefreshable) activity).displayLoad();
        }
    }

    @Override
    protected T[] doInBackground(Integer... ids) {
        if (!isCancelled()) {
            if (ids.length > 0) {
                ts = (T[]) Array.newInstance(mClass, ids.length);

                for (int i = 0; i<ids.length; i++) {
                    Array.set(ts, i, service.get(ids[i]));
                }
            } else {
                ts = service.getAll();
            }
        }

        return ts;
    }

    @Override
    protected void onPostExecute(T[] ts) {
        super.onPostExecute(ts);

        if (service.getStatusCode() == WebServiceClient.STATUS_OK) {
            if (activity instanceof IRefreshable) {
                ((IRefreshable) activity).displayContent();
            }
        } else {
            showTaskError();

            if (activity instanceof IRefreshable) {
                ((IRefreshable) activity).displayNotFound();
            }
        }
    }

    private void showTaskError() {
        CustomDialog.showOkDialog(
                activity,
                activity.getResources().getString(R.string.title_error),
                "La requête n'a pas abouti"
        );
    }
}
