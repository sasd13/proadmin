package com.sasd13.proadmin.android.util.adapter.bean2itf.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.bean.user.UserPreference;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.user.CoreInfo;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

import java.util.ArrayList;
import java.util.List;

public class UserAdapterB2I implements IAdapter<User, UserBean> {

    private UserPreferenceAdapterB2I userPreferenceAdapter;

    public UserAdapterB2I() {
        userPreferenceAdapter = new UserPreferenceAdapterB2I();
    }

    @Override
    public UserBean adapt(User s) {
        UserBean t = new UserBean();

        Id id = new Id();
        id.setId(s.getUserID());
        t.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setUserID(s.getUserID());
        coreInfo.setStatus(s.getStatus());
        coreInfo.setRoles(s.getRoles());
        coreInfo.setIntermediary(s.getIntermediary());
        coreInfo.setEmail(s.getEmail());
        t.setCoreInfo(coreInfo);

        List<UserPreferenceBean> linkedPreferences = new ArrayList<>();
        t.setLinkedPreferences(linkedPreferences);

        for (UserPreference userPreference : s.getUserPreferences().getPreferences()) {
            linkedPreferences.add(userPreferenceAdapter.adapt(userPreference));
        }

        return t;
    }
}
