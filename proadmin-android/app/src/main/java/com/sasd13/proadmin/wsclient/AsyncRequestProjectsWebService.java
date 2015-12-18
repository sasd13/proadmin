package com.sasd13.proadmin.wsclient;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.proadmin.core.bean.project.Project;

public class AsyncRequestProjectsWebService extends AsyncTask<Void, Integer, Project[]> {

    private Context context;
    private WaitDialog waitDialog;

    private ProjectsWebServiceClient service;

    public AsyncRequestProjectsWebService(Context context) {
        this.context = context;
        this.waitDialog = new WaitDialog(this.context, "Requête");
        this.service = new ProjectsWebServiceClient();

        this.waitDialog.setCancelable(true);
        this.waitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                cancel(true);
            }
        });
    }

    @Override
    protected void onPreExecute() {
        this.waitDialog.show();
    }

    @Override
    protected Project[] doInBackground(Void... aVoid) {
        while (!isCancelled()) {
            return this.service.getAll();
        }

        return null;
    }

    @Override
    protected void onCancelled() {
        this.waitDialog.dismiss();

        CustomDialog.showOkDialog(
                this.context,
                this.context.getResources().getString(com.sasd13.androidex.R.string.alertdialog_title_error),
                "Requête non aboutie"
        );
    }

    @Override
    protected void onPostExecute(Project[] projects) {
        this.waitDialog.dismiss();
    }
}
