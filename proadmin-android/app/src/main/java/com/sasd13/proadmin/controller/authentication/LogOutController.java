package com.sasd13.proadmin.controller.authentication;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.controller.ILogOutController;
import com.sasd13.proadmin.controller.MainController;

/**
 * Created by ssaidali2 on 05/12/2016.
 */
public class LogOutController extends MainController implements ILogOutController {

    public LogOutController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void entry() {
        logOut();
    }

    @Override
    public void logOut() {
        mainActivity.logOut();
    }
}
