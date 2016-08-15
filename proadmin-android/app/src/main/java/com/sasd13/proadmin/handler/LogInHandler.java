package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.LogInTask;
import com.sasd13.androidex.net.ws.rest.task.ReadTask;
import com.sasd13.proadmin.LogInActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.util.ws.EnumWSCode;
import com.sasd13.proadmin.ws.WSInformation;

/**
 * Created by ssaidali2 on 03/07/2016.
 */
public class LogInHandler implements IWSPromise {

    private LogInActivity logInActivity;
    private boolean isActionLogin;
    private LogInTask logInTask;
    private ReadTask<Teacher> readTaskTeacher;
    
    public LogInHandler(LogInActivity logInActivity) {
        this.logInActivity = logInActivity;
    }

    public void logIn(String number, String password) {
        isActionLogin = true;
        logInTask = new LogInTask(WSInformation.URL_LOGIN, number, password, this);

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
        if (logInTask.getWSCode().getCode() == EnumWSCode.OK.getCode()) {
            readTaskTeacher = new ReadTask<>(WSInformation.URL_TEACHERS, this);
            readTaskTeacher.execute(logInTask.getResult());
        } else if (logInTask.getWSCode().getCode() == EnumWSCode.ERROR_LOGIN_TEACHER_NUMBER.getCode()) {
            logInActivity.onError("Identifiant invalide");
        } else if (logInTask.getWSCode().getCode() == EnumWSCode.ERROR_LOGIN_TEACHER_PASSWORD.getCode()) {
            logInActivity.onError("Mot de passe incorrect");
        } else {
            logInActivity.onError("Impossible de se connecter");
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
    public void onFail(int httpResponseCode) {
        logInActivity.onError("Echec de la connexion au serveur");
    }
}
