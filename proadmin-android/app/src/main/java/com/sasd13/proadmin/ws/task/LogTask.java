package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.LogActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.LogWebServiceClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class LogTask extends AsyncTask<Void, Integer, Long> {

    private static final int TIMEOUT = 60000;

    private LogActivity logActivity;
    private LogWebServiceClient service;
    private String number, password;
    private Long result;
    private TaskPlanner taskPlanner;

    public LogTask(LogActivity logActivity, String number, String password) {
        this.logActivity = logActivity;
        this.number = number;
        this.password = password;
        service = new LogWebServiceClient(TIMEOUT);
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);
            }
        }, TIMEOUT - 100);
    }

    public Long getContent() {
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        taskPlanner.start();
        logActivity.displayLoad();
    }

    @Override
    protected Long doInBackground(Void... aVoid) {
        if (!isCancelled()) {
            result = service.login(number, password);
        }

        return result;
    }

    @Override
    protected void onCancelled(Long aLong) {
        doInTaskError();
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);

        taskPlanner.stop();

        if (service.getStatusCode() == LogWebServiceClient.STATUS_OK) {
            doInTaskCompleted();
        } else {
            doInTaskError();
        }
    }

    protected void doInTaskCompleted() {
        if (result == 0) {
            logActivity.displayNotFound();

            CustomDialog.showOkDialog(
                    logActivity,
                    logActivity.getResources().getString(R.string.title_error),
                    "Identifiant invalide"
            );
        } else if (result == -1) {
            logActivity.displayNotFound();

            CustomDialog.showOkDialog(
                    logActivity,
                    logActivity.getResources().getString(R.string.title_error),
                    "Mot de passe incorrect"
            );
        } else {
            logActivity.displayContent();
        }
    }

    protected void doInTaskError() {
        logActivity.displayNotFound();

        CustomDialog.showOkDialog(
                logActivity,
                logActivity.getResources().getString(R.string.title_error),
                "La requête n'a pas abouti"
        );
    }
}
