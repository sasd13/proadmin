package com.sasd13.proadmin.ws.task;

import android.app.Activity;
import android.os.AsyncTask;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.IRefreshable;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class CreateTask<T> extends AsyncTask<T, Integer, Long[]> {

    private static final int TIMEOUT = 60000;

    private Activity activity;
    private WebServiceClient<T> service;
    private Long[] ids;

    public CreateTask(Activity activity, Class<T> mClass) {
        this.activity = activity;
        service = new WebServiceClient<>(mClass, TIMEOUT);
    }

    public Long[] getContent() {
        return ids;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (activity instanceof IRefreshable) {
            ((IRefreshable) activity).displayLoad();
        }
    }

    @Override
    protected Long[] doInBackground(T... ts) {
        if (!isCancelled()) {
            if (ts.length > 0) {
                ids = new Long[ts.length];

                for (int i = 0; i<ids.length; i++) {
                    ids[i] = service.post(ts[i]);
                }
            }
        }

        return ids;
    }

    @Override
    protected void onPostExecute(Long[] ids) {
        super.onPostExecute(ids);

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
