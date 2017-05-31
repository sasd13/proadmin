package com.sasd13.proadmin.android.controller.authentication;

import android.content.Intent;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.controller.IdentityController;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.android.view.ILogInController;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInController extends IdentityController implements ILogInController {

    private Scope scope;
    private IAuthenticationService authenticationService;
    private LogInTask logInTask;

    public LogInController(IdentityActivity identityActivity, IAuthenticationService authenticationService) {
        super(identityActivity);

        scope = new Scope();
        this.authenticationService = authenticationService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void logIn(String username, String password) {
        connect(new Credential(username, password));
    }

    private void connect(Credential credential) {
        scope.setLoading(true);

        if (logInTask == null) {
            logInTask = new LogInTask(this, authenticationService);
        }

        new Requestor(logInTask).execute(credential);
    }

    void onAuthenticated(User user) {
        scope.setLoading(false);
        goToMainActivity(user);
    }

    private void goToMainActivity(User user) {
        Intent intent = new Intent(getActivity(), MainActivity.class);

        intent.putExtra(Constants.USER, user);

        startActivity(intent);
    }
}
