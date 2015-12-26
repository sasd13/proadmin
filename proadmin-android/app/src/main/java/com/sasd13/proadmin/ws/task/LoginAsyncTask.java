package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.proadmin.LogActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Samir on 24/12/2015.
 */
public class LoginAsyncTask extends AsyncTask<Void, Integer, Teacher> {

    private static final int TIMEOUT = 30000;

    private LogActivity logActivity;
    private String password;
    private Map<String, String[]> parameters;
    private WebServiceClient<Teacher> service;
    private TaskPlanner taskPlanner;
    private WaitDialog waitDialog;

    public LoginAsyncTask(LogActivity logActivity, String number, String password) {
        this.logActivity = logActivity;
        this.password = password;
        parameters = new HashMap<>();
        service = new WebServiceClient<>(Teacher.class, TIMEOUT);
        taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                cancel(true);

                waitDialog.dismiss();

                CustomDialog.showOkDialog(
                        LoginAsyncTask.this.logActivity,
                        LoginAsyncTask.this.logActivity.getResources().getString(R.string.title_error),
                        "Impossible de se connecter au serveur"
                );
            }
        }, TIMEOUT - 100);
        waitDialog = WaitDialogMaker.make(logActivity, this, taskPlanner);

        String[] numbers = {number};
        parameters.put("number", numbers);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        waitDialog.show();
        taskPlanner.start();
    }

    @Override
    protected Teacher doInBackground(Void... aVoid) {
        Teacher teacher = null;

        if (!isCancelled()) {
            try {
                teacher = service.get(parameters);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        return teacher;
    }

    @Override
    protected void onPostExecute(Teacher teacher) {
        super.onPostExecute(teacher);

        taskPlanner.stop();
        waitDialog.dismiss();

        if (teacher != null && password.equals(teacher.getPassword())) {
            Session.logIn(teacher.getId());
            logActivity.goToHomeActivity();
        } else {
            CustomDialog.showOkDialog(
                    logActivity,
                    logActivity.getResources().getString(R.string.log_dialog_title_error_log),
                    logActivity.getResources().getString(R.string.log_dialog_message_error_log)
            );
        }
    }
}
