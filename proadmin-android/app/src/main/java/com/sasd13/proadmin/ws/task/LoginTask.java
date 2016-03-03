package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.LoginActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.ws.rest.LoginWebServiceClient;

/**
 * Created by Samir on 24/12/2015.
 */
public class LoginTask extends AsyncTask<Void, Integer, Long> {

    private static final int TIMEOUT = 60000;

    private LoginActivity loginActivity;
    private LoginWebServiceClient service;
    private String number, password;
    private Long result;
    private TaskPlanner taskPlanner;

    public LoginTask(LoginActivity loginActivity, String number, String password) {
        this.loginActivity = loginActivity;
        this.number = number;
        this.password = password;
        service = new LoginWebServiceClient(TIMEOUT);
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
        loginActivity.doInLoad();
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

        if (service.getStatusCode() == LoginWebServiceClient.STATUS_OK) {
            doInTaskCompleted();
        } else {
            doInTaskError();
        }
    }

    protected void doInTaskCompleted() {
        if (result == 0) {
            loginActivity.doInError();

            CustomDialog.showOkDialog(
                    loginActivity,
                    loginActivity.getResources().getString(R.string.title_error),
                    "Identifiant invalide"
            );
        } else if (result == -1) {
            loginActivity.doInError();

            CustomDialog.showOkDialog(
                    loginActivity,
                    loginActivity.getResources().getString(R.string.title_error),
                    "Mot de passe incorrect"
            );
        } else {
            loginActivity.doInCompleted();
        }
    }

    protected void doInTaskError() {
        loginActivity.doInError();

        Toast.makeText(loginActivity, "La requête n'a pas abouti", Toast.LENGTH_SHORT).show();
    }
}
