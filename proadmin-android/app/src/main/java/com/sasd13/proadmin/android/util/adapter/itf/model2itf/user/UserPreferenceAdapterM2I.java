package com.sasd13.proadmin.android.util.adapter.itf.model2itf.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.model.user.preference.UserPreference;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

public class UserPreferenceAdapterM2I implements IAdapter<UserPreference, UserPreferenceBean> {

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
