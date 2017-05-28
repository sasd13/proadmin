package com.sasd13.proadmin.android.controller.splashscreen;

import android.content.Intent;

import com.sasd13.androidex.gui.GUIConstants;
import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.activity.SplashScreenActivity;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.controller.Controller;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.ISessionStorageService;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.android.view.ISplashScreenController;

/**
 * Created by ssaidali2 on 08/05/2017.
 */

public class SplashScreenController extends Controller implements ISplashScreenController {

    private ISessionStorageService sessionStorageService;
    private IAuthenticationService authenticationService;
    private IUserService userService;
    private UserReadTask userReadTask;

    public SplashScreenController(SplashScreenActivity splashScreenActivity, ISessionStorageService sessionStorageService, IAuthenticationService authenticationService, IUserService userService) {
        super(splashScreenActivity);

        this.sessionStorageService = sessionStorageService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public Scope getScope() {
        return null;
    }

    @Override
    public void run() {
        if (!authenticationService.isAuthenticated()) {
            goToIdentityActivity();
        } else {
            readUser();
        }
    }

    private void goToIdentityActivity() {
        startIntent(new Intent(getActivity(), IdentityActivity.class), GUIConstants.TIMEOUT_ACTIVITY / 2);
    }

    private void readUser() {
        if (userReadTask == null) {
            userReadTask = new UserReadTask(this, userService);
        }

        new Requestor(userReadTask).execute(sessionStorageService.getUserID());
    }

    void onReadUser(User user) {
        goToMainActivity(user);
    }

    private void goToMainActivity(User user) {
        Intent intent = new Intent(getActivity(), MainActivity.class);

        intent.putExtra(Constants.USER, user);

        startIntent(intent, GUIConstants.TIMEOUT_ACTIVITY / 2);
    }
}
