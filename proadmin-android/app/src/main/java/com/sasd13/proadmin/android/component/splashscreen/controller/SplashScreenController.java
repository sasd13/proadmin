package com.sasd13.proadmin.android.component.splashscreen.controller;

import android.content.Intent;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.activity.IdentityActivity;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.activity.SplashScreenActivity;
import com.sasd13.proadmin.android.component.Controller;
import com.sasd13.proadmin.android.component.splashscreen.task.UserReadTask;
import com.sasd13.proadmin.android.component.splashscreen.view.ISplashScreenController;
import com.sasd13.proadmin.android.model.user.User;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.ISessionStorageService;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.service.IUserStorageService;

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
        startActivity(new Intent(getActivity(), IdentityActivity.class));
    }

    private void readUser() {
        if (userReadTask == null) {
            userReadTask = new UserReadTask(this, userService);
        }

        new Requestor(userReadTask).execute(sessionStorageService.getUserID());
    }

    public void onReadUser(User user) {
        goToMainActivity(user);
    }

    private void goToMainActivity(User user) {
        Intent intent = new Intent(getActivity(), MainActivity.class);

        intent.putExtra(IUserStorageService.KEY_USER, user);
        startActivity(intent);
    }
}
