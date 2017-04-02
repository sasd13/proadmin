package com.sasd13.proadmin.controller.authentication;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.IdentityActivity;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.controller.ILogInController;
import com.sasd13.proadmin.controller.IdentityController;
import com.sasd13.proadmin.service.IAuthenticationService;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.util.EnumErrorRes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LogInController extends IdentityController implements ILogInController {

    private IAuthenticationService authenticationService;
    private ITeacherService teacherService;
    private LogInStrategy logInStrategy;
    private WaitDialog waitDialog;

    public LogInController(IdentityActivity identityActivity, IAuthenticationService authenticationService, ITeacherService teacherService) {
        super(identityActivity);

        this.authenticationService = authenticationService;
        this.teacherService = teacherService;
    }

    @Override
    public void entry() {
        identityActivity.clearHistory();
    }

    @Override
    public void logIn(String username, String password) {
        Map<String, String> credentials = new HashMap<>();

        credentials.put(IAuthenticationService.PARAMETER_USERNAME, username);
        credentials.put(IAuthenticationService.PARAMETER_PASSWORD, password);

        connect(credentials);
    }

    private void connect(Map<String, String> credentials) {
        if (logInStrategy == null) {
            logInStrategy = new LogInStrategy(this, authenticationService, teacherService);
        }

        new Requestor(logInStrategy).execute(credentials);
    }

    void onWaiting() {
        waitDialog = new WaitDialog(identityActivity);
        waitDialog.show();
    }

    void onReadTeacher(Teacher teacher) {
        waitDialog.dismiss();
        identityActivity.goToMainActivity(teacher);
    }

    void onFail(int code) {
        waitDialog.dismiss();
        display(EnumErrorRes.find(code).getStringRes());
    }
}
