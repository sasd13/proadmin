package com.sasd13.proadmin.aaa.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.User;
import com.sasd13.proadmin.aaa.bean.UserCreate;
import com.sasd13.proadmin.itf.bean.user.create.UserCreateBean;

public class UserCreateAdapterI2B implements IAdapter<UserCreateBean, UserCreate> {

	@Override
	public UserCreate adapt(UserCreateBean s) {
		UserCreate t = new UserCreate();

		User user = new User();
		user.setRoles(s.getCoreInfo().getRoles());
		user.setIntermediary(s.getCoreInfo().getIntermediary());
		user.setEmail(s.getCoreInfo().getEmail());
		t.setUser(user);

		Credential credential = new Credential();
		credential.setUsername(s.getLinkedInfo().getUsername());
		credential.setPassword(s.getLinkedInfo().getPassword());
		t.setCredential(credential);

		return t;
	}
}
