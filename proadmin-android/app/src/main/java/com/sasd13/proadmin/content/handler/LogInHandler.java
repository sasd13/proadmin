package com.sasd13.proadmin.content.handler;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.proadmin.LogInActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.util.Promise;
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
        waitDialog = new WaitDialog(logInActivity);
    }

    public void logIn(String number, String password) {
        isActionLogin = true;
        logInTask = new LogInTask(this, number, password);

        logInTask.execute();
    }

    @Override
    public void onLoad() {
        if (isActionLogin) {
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
        if (logInTask.getResult() == LoginWebServiceCode.ERROR_TEACHER_NUMBER.getValue()) {
            logInActivity.onError("Identifiant invalide");
        } else if (logInTask.getResult() == LoginWebServiceCode.ERROR_TEACHER_PASSWORD.getValue()) {
            logInActivity.onError("Mot de passe incorrect");
        } else {
            readTaskTeacher = new ReadTask<>(this, Teacher.class);
            readTaskTeacher.execute(logInTask.getResult());
        }
    }

    private void onReadTaskTeacherSucceeded() {
        try {
            Cache.keep(readTaskTeacher.getResults().get(0));
            logInActivity.onLogInSucceeded(readTaskTeacher.getResults().get(0));
        } catch (IndexOutOfBoundsException e) {
            logInActivity.onError("Erreur de chargement des données");
        }
    }

    @Override
    public void onFail() {
        logInActivity.onError("Echec lors de la tentative de connexion");
    }
}
