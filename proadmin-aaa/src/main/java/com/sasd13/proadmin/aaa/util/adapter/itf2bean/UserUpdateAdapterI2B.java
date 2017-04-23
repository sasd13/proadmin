package com.sasd13.proadmin.aaa.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.bean.UserUpdate;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;

public class UserUpdateAdapterI2B implements IAdapter<UserUpdateBean, UserUpdate> {

	private UserAdapterI2B userAdapter;
	private CredentialUpdateAdapterI2B credentialUpdateAdapter;

	public UserUpdateAdapterI2B() {
		userAdapter = new UserAdapterI2B();
		credentialUpdateAdapter = new CredentialUpdateAdapterI2B();
	}

	@Override
	public UserUpdate adapt(UserUpdateBean s) {
		UserUpdate t = new UserUpdate();

		if (s.getUser() != null) {
			t.setUser(userAdapter.adapt(s.getUser()));
		}

		if (s.getCedits() != null) {
			t.setCredentials(credentialUpdateAdapter.adapt(s.getCedits()));
		}

		return t;
	}
}
