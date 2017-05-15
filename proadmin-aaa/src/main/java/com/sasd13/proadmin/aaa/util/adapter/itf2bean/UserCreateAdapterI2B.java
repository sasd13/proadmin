package com.sasd13.proadmin.aaa.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.bean.EnumStatus;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.itf.bean.user.create.UserCreateBean;

public class UserCreateAdapterI2B implements IAdapter<UserCreateBean, User> {

	private static final String DELIMITER = ";";

	@Override
	public User adapt(UserCreateBean s) {
		User t = new User();

		t.setUsername(s.getLinkedCredential().getUsername());
		t.setPassword(s.getLinkedCredential().getPassword());
		t.setStatus(EnumStatus.INITIAL);
		t.setRoles(String.join(DELIMITER, s.getCoreInfo().getRoles()));
		t.setIntermediary(s.getCoreInfo().getIntermediary());
		t.setEmail(s.getCoreInfo().getEmail());

		return t;
	}
}
