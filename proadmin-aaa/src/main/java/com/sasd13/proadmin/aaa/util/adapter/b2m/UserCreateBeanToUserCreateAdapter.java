package com.sasd13.proadmin.aaa.util.adapter.b2m;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.UserCreate;
import com.sasd13.proadmin.itf.bean.user.UserCreateBean;

public class UserCreateBeanToUserCreateAdapter implements IAdapter<UserCreateBean, UserCreate> {

	@Override
	public UserCreate adapt(UserCreateBean source) {
		UserCreate target = new UserCreate();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(UserCreateBean source, UserCreate target) {
		target.setUser(new UserBeanToUserAdapter().adapt(source.getUser()));
		target.setCredential(target.getCredential());
	}
}
