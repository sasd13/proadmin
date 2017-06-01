package com.sasd13.proadmin.android.view;

import com.sasd13.proadmin.android.bean.user.UserUpdate;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ISettingController extends IController, IBrowsable {

    void actionReadUser();

    void actionUpdateUser(UserUpdate userUpdate);
}
