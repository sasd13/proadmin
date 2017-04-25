package com.sasd13.proadmin.android.controller.authentication;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.controller.IdentityController;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.view.ILogInController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInController extends IdentityController implements ILogInController {

    private IAuthenticationService authenticationService;
    private LogInTask logInTask;
    private WaitDialog waitDialog;

    public LogInController(IdentityActivity identityActivity, IAuthenticationService authenticationService) {
        super(identityActivity);

        this.authenticationService = authenticationService;
    }

    @Override
    public Object getScope() {
        return null;
    }

    @Override
    public void logIn(String username, String password) {
        Map<String, String> credentials = new HashMap<>();

        credentials.put(IAuthenticationService.PARAMETER_USERNAME, username);
        credentials.put(IAuthenticationService.PARAMETER_PASSWORD, password);

        connect(credentials);
    }

    private void connect(Map<String, String> credentials) {
        if (logInTask == null) {
            logInTask = new LogInTask(this, authenticationService);
        }

        new Requestor(logInTask).execute(credentials);
    }

    void onWaiting() {
        waitDialog = new WaitDialog(identityActivity);
        waitDialog.show();
    }

    void onReadTeacher(String userID, String intermediary) {
        waitDialog.dismiss();
        identityActivity.goToMainActivity(userID, intermediary);
    }

    @Override
    public void onFail(int httpStatus, List<String> responseErrors) {
        waitDialog.dismiss();
        super.onFail(httpStatus, responseErrors);
    }
}
