package com.sasd13.proadmin.aaa.util.adapter.b2m;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.UserUpdate;
import com.sasd13.proadmin.itf.bean.user.UserUpdateBean;

public class UserUpdateBeanToUserUpdateAdapter implements IAdapter<UserUpdateBean, UserUpdate> {

	@Override
	public UserUpdate adapt(UserUpdateBean source) {
		UserUpdate target = new UserUpdate();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(UserUpdateBean source, UserUpdate target) {
		target.setUser(new UserBeanToUserAdapter().adapt(source.getUser()));
		target.setCredentials(new CredentialUpdateBeanToCredentialUpdateAdapter().adapt(source.getCredentials()));
		target.setUserID(source.getUserID());
	}
}
