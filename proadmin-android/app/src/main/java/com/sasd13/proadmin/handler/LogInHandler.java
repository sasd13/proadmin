package com.sasd13.proadmin.handler;

import com.sasd13.proadmin.LogInActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.util.Promise;
import com.sasd13.proadmin.util.code.ws.EnumWSCode;
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
    
    public LogInHandler(LogInActivity logInActivity) {
        this.logInActivity = logInActivity;
    }

    public void logIn(String number, String password) {
        isActionLogin = true;
        logInTask = new LogInTask(this, number, password);

        logInTask.execute();
    }

    @Override
    public void onLoad() {
        if (isActionLogin) {
            logInActivity.onLoad();
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
        if (logInTask.getResult() == EnumWSCode.LOGIN_ERROR_TEACHER_NUMBER.getValue()) {
            logInActivity.onError("Identifiant invalide");
        } else if (logInTask.getResult() == EnumWSCode.LOGIN_ERROR_TEACHER_PASSWORD.getValue()) {
            logInActivity.onError("Mot de passe incorrect");
        } else {
            readTaskTeacher = new ReadTask<>(this, Teacher.class);
            readTaskTeacher.execute(logInTask.getResult());
        }
    }

    private void onReadTaskTeacherSucceeded() {
        try {
            Teacher teacher = readTaskTeacher.getResults().get(0);

            Cache.keep(logInActivity, teacher);
            logInActivity.onLogInSucceeded(teacher);
        } catch (IndexOutOfBoundsException e) {
            logInActivity.onError("Erreur de chargement des données");
        }
    }

    @Override
    public void onFail() {
        logInActivity.onError("Echec de la connexion au serveur");
    }
}
