package com.sasd13.proadmin.android.util.adapter.itf2bean.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.bean.user.UserPreference;
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

        t.setUserID(s.getCoreInfo().getUserID());
        t.setStatus(s.getCoreInfo().getStatus());
        t.setRoles(s.getCoreInfo().getRoles());
        t.setIntermediary(s.getCoreInfo().getIntermediary());
        t.setEmail(s.getCoreInfo().getEmail());

        List<UserPreference> userPreferences = new ArrayList<>();
        t.setUserPreferences(userPreferences);

        for (UserPreferenceBean userPreferenceBean : s.getLinkedPreferences()) {
            userPreferences.add(userPreferenceAdapter.adapt(userPreferenceBean));
        }

        return t;
    }
}
