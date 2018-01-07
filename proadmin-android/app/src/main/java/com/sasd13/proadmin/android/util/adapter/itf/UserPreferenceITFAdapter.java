package com.sasd13.proadmin.android.util.adapter.itf;

import com.sasd13.proadmin.android.model.user.preference.UserPreference;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.user.UserPreferenceAdapterI2M;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.user.UserPreferenceAdapterM2I;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

/**
 * Created by ssaidali2 on 07/01/2018.
 */

public class UserPreferenceITFAdapter extends ITFAdapter<UserPreference, UserPreferenceBean> {

    public UserPreferenceITFAdapter() {
        super(new UserPreferenceAdapterI2M(), new UserPreferenceAdapterM2I());
    }
}
