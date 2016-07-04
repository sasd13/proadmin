package com.sasd13.proadmin.content.handler;

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

    private LogInActivity logInActivity;
    private boolean isActionLogin;
    private LogInTask logInTask;
    private ReadTask<Teacher> readTaskTeacher;
    
    private LogInHandler() {}

    public static LogInHandler getInstance() {
        return LogInHandlerHolder.INSTANCE;
    }

    public void logIn(LogInActivity logInActivity, String number, String password) {
        this.logInActivity = logInActivity;
        logInTask = new LogInTask(this, number, password);
        isActionLogin = true;

        logInTask.execute();
    }

    @Override
    public void onLoad() {
        if (isActionLogin) {
            logInActivity.onLogInLoad();
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
            logInActivity.onLogInError(error);
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
            logInActivity.onLogInError(error);
        } else {
            logInActivity.onLogInSuccess(readTaskTeacher.getResults().get(0));
        }
    }

    @Override
    public void onFail() {
        logInActivity.onLogInError("Echec lors de la tentative de connexion");
    }
}
