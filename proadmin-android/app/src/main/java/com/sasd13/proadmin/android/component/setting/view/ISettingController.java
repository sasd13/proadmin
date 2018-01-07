package com.sasd13.proadmin.android.component.setting.view;

import com.sasd13.proadmin.android.model.user.UserUpdate;
import com.sasd13.proadmin.android.util.browser.IBrowsable;
import com.sasd13.proadmin.android.component.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ISettingController extends IController, IBrowsable {

    void actionReadUser();

    void actionUpdateUser(UserUpdate userUpdate);
}
