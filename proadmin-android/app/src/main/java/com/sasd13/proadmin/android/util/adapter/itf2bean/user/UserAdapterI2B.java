package com.sasd13.proadmin.android.util.adapter.itf2bean.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.bean.user.preference.UserPreference;
import com.sasd13.proadmin.android.bean.user.preference.UserPreferences;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

import java.util.ArrayList;
import java.util.List;

public class UserAdapterI2B implements IAdapter<UserBean, User> {

    private UserPreferenceAdapterI2B userPreferenceAdapter;

    public UserAdapterI2B() {
        userPreferenceAdapter = new UserPreferenceAdapterI2B();
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
