package com.sasd13.proadmin.android.util.adapter.bean2itf.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.user.UserPreference;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

public class UserPreferenceAdapterB2I implements IAdapter<UserPreference, UserPreferenceBean> {

    @Override
    public UserPreferenceBean adapt(UserPreference s) {
        UserPreferenceBean t = new UserPreferenceBean();

        t.setId(String.valueOf(s.getId()));
        t.setValue(s.getValue());
        t.setCategory(s.getCategory());
        t.setName(s.getName());
        t.setDefaultValue(s.getDefaultValue());

        return t;
    }
}
