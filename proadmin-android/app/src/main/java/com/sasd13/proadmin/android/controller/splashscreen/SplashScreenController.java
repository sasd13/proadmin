package com.sasd13.proadmin.android.controller.splashscreen;

import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.activity.SplashScreenActivity;
import com.sasd13.proadmin.android.bean.User;
import com.sasd13.proadmin.android.bean.UserPreference;
import com.sasd13.proadmin.android.controller.Controller;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.android.view.ISplashScreenController;

/**
 * Created by ssaidali2 on 08/05/2017.
 */

public class SplashScreenController extends Controller implements ISplashScreenController {

    private IUserService userService;
    private SessionStorage sessionStorage;
    private UserReadTask userReadTask;

    public SplashScreenController(SplashScreenActivity splashScreenActivity, IUserService userService) {
        super(splashScreenActivity);

        this.userService = userService;
        sessionStorage = splashScreenActivity.getSessionStorage();
    }

    @Override
    public Scope getScope() {
        return null;
    }

    @Override
    public SplashScreenActivity getActivity() {
        return (SplashScreenActivity) super.getActivity();
    }

    @Override
    public void run() {
        if (isAuthenticated()) {
            readUser();
        } else {
            getActivity().goToIdentityActivity();
        }
    }

    private boolean isAuthenticated() {
        return sessionStorage.contains(Constants.USERID) && sessionStorage.contains(Constants.INTERMEDIARY);
    }

    private void readUser() {
        if (userReadTask == null) {
            userReadTask = new UserReadTask(this, userService);
        }

        new Requestor(userReadTask).execute(sessionStorage.get(Constants.USERID));
    }

    void onReadUser(User user) {
        //TODO
        user.setPreferences(new UserPreference(getActivity()));

        getActivity().goToMainActivity(user.getPreferences());
    }
}
