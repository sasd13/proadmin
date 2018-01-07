package com.sasd13.proadmin.android.component.setting.scope;

import com.sasd13.proadmin.android.component.Scope;
import com.sasd13.proadmin.android.model.user.UserUpdate;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class SettingScope extends Scope {

    private UserUpdate userUpdate;

    public UserUpdate getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(UserUpdate userUpdate) {
        this.userUpdate = userUpdate;

        setChanged();
        notifyObservers();
    }
}
