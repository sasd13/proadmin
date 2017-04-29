package com.sasd13.proadmin.android.controller.setting;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.User;
import com.sasd13.proadmin.android.bean.update.UserUpdate;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.scope.SettingScope;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.view.ISettingController;
import com.sasd13.proadmin.android.view.fragment.setting.SettingFragment;
import com.sasd13.proadmin.util.EnumParameter;

public class SettingController extends MainController implements ISettingController {

    private SettingScope scope;
    private IUserService userService;
    private UserReadTask userReadTask;
    private UserUpdateTask userUpdateTask;

    public SettingController(MainActivity mainActivity, IUserService userService) {
        super(mainActivity);

        scope = new SettingScope();
        this.userService = userService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        startFragment(SettingFragment.newInstance());
        actionRefresh();
    }

    @Override
    public void actionRefresh() {
        readUser();
    }

    private void readUser() {
        scope.setLoading(true);

        if (userReadTask == null) {
            userReadTask = new UserReadTask(this, userService);
        }

        userReadTask.clearParameters();
        userReadTask.putParameter(EnumParameter.USERID.getName(), new String[]{SessionHelper.getExtraUserID(mainActivity)});
        new Requestor(userReadTask).execute();
    }

    void onReadUser(User user) {
        scope.setUser(user);
        scope.setLoading(false);
    }

    @Override
    public void actionUpdateUser(UserUpdate userUpdate) {
        if (userUpdateTask == null) {
            userUpdateTask = new UserUpdateTask(this, userService);
        }

        new Requestor(userUpdateTask).execute(userUpdate);
    }

    void onUpdateUser() {
        display(R.string.message_updated);
    }
}