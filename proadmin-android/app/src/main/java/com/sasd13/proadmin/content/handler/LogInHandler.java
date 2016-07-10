package com.sasd13.proadmin.content.handler;

import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.proadmin.LogInActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.code.ws.LoginWebServiceCode;
import com.sasd13.proadmin.ws.task.LogInTask;
import com.sasd13.proadmin.ws.task.ReadTask;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInHandler implements Promise {

    private LogInActivity logInActivity;
    private boolean isActionLogin;
    private LogInTask logInTask;
    private ReadTask<Teacher> readTaskTeacher;
    private WaitDialog waitDialog;
    
    public LogInHandler(LogInActivity logInActivity) {
        this.logInActivity = logInActivity;
    }

    public void logIn(String number, String password) {
        logInTask = new LogInTask(this, number, password);
        isActionLogin = true;

        logInTask.execute();
    }

    @Override
    public void onLoad() {
        if (isActionLogin) {
            waitDialog = new WaitDialog(logInActivity);
            waitDialog.show();
        }
    }

    @Override
    public void onSuccess() {
        if (isActionLogin) {
            isActionLogin = false;

            onLogInTaskSucceeded();
        } else {
            onReadTaskTeacherSucceeded();
        }
    }

    private void onLogInTaskSucceeded() {
        String error = null;

        if (logInTask.getResult() == LoginWebServiceCode.ERROR_TEACHER_NUMBER.getValue()) {
            error = "Identifiant invalide";
        } else if (logInTask.getResult() == LoginWebServiceCode.ERROR_TEACHER_PASSWORD.getValue()) {
            error = "Mot de passe incorrect";
        }

        if (error != null) {
            onError(error);
        } else {
            readTaskTeacher = new ReadTask<>(this, Teacher.class);
            readTaskTeacher.execute(logInTask.getResult());
        }
    }

    private void onReadTaskTeacherSucceeded() {
        String error = null;

        try {
            Cache.keep(readTaskTeacher.getResults().get(0));
        } catch (IndexOutOfBoundsException e) {
            error = "Erreur de chargement des données";
        }

        if (error != null) {
            onError(error);
        } else {
            onSuccess(readTaskTeacher.getResults().get(0));
        }
    }

    @Override
    public void onFail() {
        onError("Echec lors de la tentative de connexion");
    }

    public void onSuccess(Teacher teacher) {
        waitDialog.dismiss();
        SessionHelper.logIn(logInActivity, teacher);
    }

    public void onError(String error) {
        waitDialog.dismiss();
        Toast.makeText(logInActivity, error, Toast.LENGTH_SHORT).show();
    }
}
