package com.sasd13.proadmin.android.util.adapter.itf.itf2model.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.model.user.preference.UserPreference;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

public class UserPreferenceAdapterI2M implements IAdapter<UserPreferenceBean, UserPreference> {

    @Override
    public UserPreference adapt(UserPreferenceBean s) {
        UserPreference t = new UserPreference();

        t.setId(Long.valueOf(s.getId()));
        t.setValue(s.getValue());
        t.setCategory(s.getCategory());
        t.setName(s.getName());
        t.setDefaultValue(s.getDefaultValue());

        return t;
    }
}
