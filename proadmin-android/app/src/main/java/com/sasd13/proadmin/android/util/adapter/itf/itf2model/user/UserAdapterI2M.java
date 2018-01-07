package com.sasd13.proadmin.android.util.adapter.itf.itf2model.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.model.user.User;
import com.sasd13.proadmin.android.model.user.preference.UserPreference;
import com.sasd13.proadmin.android.model.user.preference.UserPreferences;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

import java.util.ArrayList;
import java.util.List;

public class UserAdapterI2M implements IAdapter<UserBean, User> {

    private UserPreferenceAdapterI2M userPreferenceAdapter;

    public UserAdapterI2M() {
        userPreferenceAdapter = new UserPreferenceAdapterI2M();
    }

    @Override
    public User adapt(UserBean s) {
        User t = new User();

        t.setId(Long.valueOf(s.getId().getId()));
        t.setUserID(s.getCoreInfo().getUserID());
        t.setStatus(s.getCoreInfo().getStatus());
        t.setRoles(s.getCoreInfo().getRoles());
        t.setIntermediary(s.getCoreInfo().getIntermediary());
        t.setEmail(s.getCoreInfo().getEmail());

        List<UserPreference> preferences = new ArrayList<>();

        for (UserPreferenceBean userPreferenceBean : s.getLinkedPreferences()) {
            preferences.add(userPreferenceAdapter.adapt(userPreferenceBean));
        }

        t.setUserPreferences(new UserPreferences(preferences));

        return t;
    }
}
