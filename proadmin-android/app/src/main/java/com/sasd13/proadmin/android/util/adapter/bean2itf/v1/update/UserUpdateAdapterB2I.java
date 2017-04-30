package com.sasd13.proadmin.android.util.adapter.bean2itf.v1.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.UserUpdate;
import com.sasd13.proadmin.android.util.adapter.bean2itf.UserAdapterB2I;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;

public class UserUpdateAdapterB2I implements IAdapter<UserUpdate, UserUpdateBean> {

    private UserAdapterB2I userAdapter;
    private CredentialUpdateAdapterB2I credentialUpdateAdapter;

    public UserUpdateAdapterB2I() {
        userAdapter = new UserAdapterB2I();
        credentialUpdateAdapter = new CredentialUpdateAdapterB2I();
    }

    @Override
    public UserUpdateBean adapt(UserUpdate s) {
        UserUpdateBean t = new UserUpdateBean();

        if (s.getUser() != null) {
            t.setUser(userAdapter.adapt(s.getUser()));
        }

        if (s.getCredentials() != null) {
            t.setCredentials(credentialUpdateAdapter.adapt(s.getCredentials()));
        }

        return t;
    }
}
