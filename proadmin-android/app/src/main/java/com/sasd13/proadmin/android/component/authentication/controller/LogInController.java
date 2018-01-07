package com.sasd13.proadmin.android.component.authentication.controller;

import android.content.Intent;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.component.IdentityController;
import com.sasd13.proadmin.android.component.authentication.task.LogInTask;
import com.sasd13.proadmin.android.component.authentication.view.ILogInController;
import com.sasd13.proadmin.android.model.user.User;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.IUserStorageService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInController extends IdentityController implements ILogInController {

    private Scope scope;
    private IAuthenticationService authenticationService;
    private IUserStorageService userStorageService;
    private LogInTask logInTask;

    public LogInController(IdentityActivity identityActivity, IAuthenticationService authenticationService, IUserStorageService userStorageService) {
        super(identityActivity);

        scope = new Scope();
        this.authenticationService = authenticationService;
        this.userStorageService = userStorageService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void actionLogIn(String username, String password) {
        logIn(new Credential(username, password));
    }

    private void logIn(Credential credential) {
        scope.setLoading(true);

        if (logInTask == null) {
            logInTask = new LogInTask(this, authenticationService);
        }

        new Requestor(logInTask).execute(credential);
    }

    public void onLogIn(User user) {
        scope.setLoading(false);
        goToMainActivity(user);
    }

    private void goToMainActivity(User user) {
        Intent intent = new Intent(getActivity(), MainActivity.class);

        userStorageService.write(user);
        startActivity(intent);
    }
}
