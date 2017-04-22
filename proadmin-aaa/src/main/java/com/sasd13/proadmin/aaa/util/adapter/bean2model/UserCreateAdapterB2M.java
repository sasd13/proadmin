package com.sasd13.proadmin.aaa.util.adapter.bean2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.UserCreate;
import com.sasd13.proadmin.itf.bean.user.UserCreateBean;

public class UserCreateAdapterB2M implements IAdapter<UserCreateBean, UserCreate> {

	@Override
	public UserCreate adapt(UserCreateBean s) {
		UserCreate t = new UserCreate();

		t.setUser(new UserAdapterB2M().adapt(s.getUser()));
		t.setCredential(t.getCredential());

		return t;
	}
}
