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

    private static class LogInHandlerHolder {
        private static final LogInHandler INSTANCE = new LogInHandler();
    }

    private boolean isActionLogin;
    private LogInTask logInTask;
    private ReadTask<Teacher> readTaskTeacher;
    private WaitDialog waitDialog;
    
    private LogInHandler() {}

    public static LogInHandler getInstance() {
        return LogInHandlerHolder.INSTANCE;
    }

    public void logIn(String number, String password) {
        isActionLogin = true;
        logInTask = new LogInTask(this, number, password);
        waitDialog = new WaitDialog(LogInActivity.self);

        logInTask.execute();
    }

    @Override
    public void onLoad() {
        waitDialog.show();
    }

    @Override
    public void onSuccess() {
        if (isActionLogin) {
            isActionLogin = false;

            loginTaskSucceeded();
        } else {
            readTaskTeacherSucceeded();
        }
    }

    private void loginTaskSucceeded() {
        String error = null;

        if (logInTask.getResult() == LoginWebServiceCode.ERROR_TEACHER_NUMBER.getValue()) {
            error = "Identifiant invalide";
        } else if (logInTask.getResult() == LoginWebServiceCode.ERROR_TEACHER_PASSWORD.getValue()) {
            error = "Mot de passe incorrect";
        }

        if (error != null) {
            waitDialog.dismiss();
            LogInActivity.self.onError(error);
        } else {
            readTaskTeacher = new ReadTask<>(this, Teacher.class);
            readTaskTeacher.execute(logInTask.getResult());
        }
    }

    private void readTaskTeacherSucceeded() {
        String error = null;

        try {
            Cache.keep(readTaskTeacher.getResults().get(0));
        } catch (IndexOutOfBoundsException e) {
            error = "Erreur de chargement des données";
        }

        if (error != null) {
            waitDialog.dismiss();
            LogInActivity.self.onError(error);
        } else {
            waitDialog.dismiss();
            LogInActivity.self.onSuccess(readTaskTeacher.getResults().get(0));
        }
    }

    @Override
    public void onFail() {
        waitDialog.dismiss();
        LogInActivity.self.onError("Echec lors de la tentative de connexion");
    }
}
