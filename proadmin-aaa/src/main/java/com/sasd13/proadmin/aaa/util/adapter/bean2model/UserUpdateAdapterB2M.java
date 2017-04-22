package com.sasd13.proadmin.aaa.util.adapter.bean2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.UserUpdate;
import com.sasd13.proadmin.itf.bean.user.UserUpdateBean;

public class UserUpdateAdapterB2M implements IAdapter<UserUpdateBean, UserUpdate> {

	@Override
	public UserUpdate adapt(UserUpdateBean s) {
		UserUpdate t = new UserUpdate();

		t.setUser(new UserAdapterB2M().adapt(s.getUser()));
		t.setCredentials(new CredentialUpdateAdapterB2M().adapt(s.getCredentials()));

		return t;
	}
}
