package com.sasd13.proadmin.ws.task;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.proadmin.LogActivity;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.session.Session;
import com.sasd13.proadmin.ws.rest.WebServiceClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Samir on 24/12/2015.
 */
public class LoginAsyncTask extends AsyncTask<Void, Integer, Teacher> {

    private static final int MAX_TRY = 2000;
    private static final int DELAY = 10;

    private LogActivity logActivity;
    private String password;
    private Map<String, String[]> mapParams;
    private WebServiceClient<Teacher> service;
    private WaitDialog waitDialog;
    private int count;

    public LoginAsyncTask(LogActivity logActivity, String number, String password) {
        this.logActivity = logActivity;
        this.password = password;
        mapParams = new HashMap<>();
        service = new WebServiceClient<>(Teacher.class);
        waitDialog = WaitDialogMaker.make(logActivity, this);

        String[] numbers = {number};
        mapParams.put("number", numbers);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        waitDialog.show();
    }

    @Override
    protected Teacher doInBackground(Void... aVoid) {
        Teacher teacher = null;

        if (!isCancelled()) {
            if (count > MAX_TRY) {
                cancel(true);
            } else {
                count++;

                teacher = (service.getAll(mapParams))[0];

                SystemClock.sleep(DELAY);
            }
        }

        return teacher;
    }

    @Override
    protected void onCancelled(Teacher teacher) {
        if (count > MAX_TRY) {
            CustomDialog.showOkDialog(
                    logActivity,
                    logActivity.getString(R.string.log_dialog_title_error_log),
                    "Impossible de se connecter");
        }
    }

    @Override
    protected void onPostExecute(Teacher teacher) {
        super.onPostExecute(teacher);

        waitDialog.dismiss();

        if (teacher != null && password.equals(teacher.getPassword())) {
            Session.logIn(teacher);
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
