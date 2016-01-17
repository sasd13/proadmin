package com.sasd13.proadmin.ws.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class DeleteTask<T> extends AsyncTask<Long, Integer, Void> {

    private static final int TIMEOUT = 60000;

    private Context context;
    private WebServiceClient<T> service;

    public DeleteTask(Context context, Class<T> mClass) {
        this.context = context;
        service = new WebServiceClient<>(mClass, TIMEOUT);
    }

    @Override
    protected Void doInBackground(Long... ids) {
        if (!isCancelled()) {
            for (int i=0; i < ids.length; i++) {
                service.delete(ids[i]);
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (service.getStatusCode() == WebServiceClient.STATUS_OK) {
            Toast.makeText(context, context.getResources().getString(R.string.message_deleted), Toast.LENGTH_SHORT).show();
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
