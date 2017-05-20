package com.sasd13.proadmin.android.util.adapter.bean2itf.user;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.user.CredentialUpdate;
import com.sasd13.proadmin.itf.bean.user.LinkedCredential;
import com.sasd13.proadmin.itf.bean.user.update.CredentialUpdateBean;

public class CredentialUpdateAdapterB2I implements IAdapter<CredentialUpdate, CredentialUpdateBean> {

    @Override
    public CredentialUpdateBean adapt(CredentialUpdate s) {
        CredentialUpdateBean t = new CredentialUpdateBean();

        LinkedCredential previous = new LinkedCredential();
        previous.setUsername(s.getPrevious().getUsername());
        previous.setPassword(s.getPrevious().getPassword());
        t.setPrevious(previous);

        LinkedCredential current = new LinkedCredential();
        current.setUsername(s.getCurrent().getUsername());
        current.setPassword(s.getCurrent().getPassword());
        t.setCurrent(current);

        return t;
    }
}
