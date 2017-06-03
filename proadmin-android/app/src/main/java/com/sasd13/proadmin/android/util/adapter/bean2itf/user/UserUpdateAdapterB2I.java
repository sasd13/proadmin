package com.sasd13.proadmin.android.util.adapter.bean2itf.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.user.preference.UserPreference;
import com.sasd13.proadmin.android.bean.user.UserUpdate;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.user.CoreInfo;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;

import java.util.ArrayList;
import java.util.List;

public class UserUpdateAdapterB2I implements IAdapter<UserUpdate, UserUpdateBean> {

    private UserPreferenceAdapterB2I userPreferenceAdapter;
    private CredentialUpdateAdapterB2I credentialUpdateAdapter;

    public UserUpdateAdapterB2I() {
        userPreferenceAdapter = new UserPreferenceAdapterB2I();
        credentialUpdateAdapter = new CredentialUpdateAdapterB2I();
    }

    @Override
    public UserUpdateBean adapt(UserUpdate s) {
        UserUpdateBean t = new UserUpdateBean();

        UserBean userBean = new UserBean();
        t.setUser(userBean);

        Id id = new Id();
        id.setId(String.valueOf(s.getUser().getId()));
        userBean.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setUserID(s.getUser().getUserID());
        coreInfo.setStatus(s.getUser().getStatus());
        coreInfo.setRoles(s.getUser().getRoles());
        coreInfo.setIntermediary(s.getUser().getIntermediary());
        coreInfo.setEmail(s.getUser().getEmail());
        userBean.setCoreInfo(coreInfo);

        if (s.getUser().getUserPreferences() != null) {
            List<UserPreferenceBean> linkedPreferences = new ArrayList<>();
            userBean.setLinkedPreferences(linkedPreferences);

            for (UserPreference userPreference : s.getUser().getUserPreferences().getPreferences()) {
                linkedPreferences.add(userPreferenceAdapter.adapt(userPreference));
            }
        }

        if (s.getCredentials() != null) {
            t.setCredentials(credentialUpdateAdapter.adapt(s.getCredentials()));
        }

        return t;
    }
}
