package com.sasd13.proadmin.android.controller.authentication;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.controller.IdentityController;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.v1.IAuthenticationService;
import com.sasd13.proadmin.android.util.Extra;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.view.ILogInController;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> credentials = new HashMap<>();

        credentials.put(IAuthenticationService.PARAMETER_USERNAME, username);
        credentials.put(IAuthenticationService.PARAMETER_PASSWORD, password);

        connect(credentials);
    }

    private void connect(Map<String, String> credentials) {
        scope.setLoading(true);

        if (logInTask == null) {
            logInTask = new LogInTask(this, authenticationService);
        }

        new Requestor(logInTask).execute(credentials);
    }

    void onReadUser(String userID, String intermediary) {
        SessionHelper.setExtra(identityActivity, Extra.USERID, userID);
        SessionHelper.setExtra(identityActivity, Extra.INTERMEDIARY, intermediary);

        scope.setLoading(false);
        identityActivity.goToMainActivity();
    }
}
