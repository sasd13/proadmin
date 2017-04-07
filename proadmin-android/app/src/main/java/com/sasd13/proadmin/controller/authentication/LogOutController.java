package com.sasd13.proadmin.controller.authentication;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.view.gui.browser.IBrowsable;
import com.sasd13.proadmin.view.fragment.authentication.ILogOutController;
import com.sasd13.proadmin.controller.MainController;

/**
 * Created by ssaidali2 on 05/12/2016.
 */
public class LogOutController extends MainController implements ILogOutController, IBrowsable {

    public LogOutController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public Object getScope() {
        return null;
    }

    @Override
    public void browse() {
        logOut();
    }

    @Override
    public void logOut() {
        mainActivity.logOut();
    }
}
