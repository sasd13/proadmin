package com.sasd13.proadmin.controller.authentication;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.IdentityActivity;
import com.sasd13.proadmin.controller.IdentityController;
import com.sasd13.proadmin.service.IAuthenticationService;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.view.ILogInController;

import java.util.HashMap;
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

    void onFail(int code) {
        waitDialog.dismiss();
        display(EnumErrorRes.find(code).getResID());
    }
}
