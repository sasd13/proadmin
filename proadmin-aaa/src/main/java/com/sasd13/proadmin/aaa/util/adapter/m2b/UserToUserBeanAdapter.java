package com.sasd13.proadmin.aaa.util.adapter.m2b;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.itf.bean.user.UserBean;

public class UserToUserBeanAdapter implements IAdapter<User, UserBean> {

	@Override
	public UserBean adapt(User source) {
		UserBean target = new UserBean();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(User source, UserBean target) {
		target.setUserID(source.getUserID());
		target.setStatus(source.getStatus().getCode());
		target.setRoles(source.getRoles());
		target.setIntermediary(source.getIntermediary());
		target.setEmail(source.getEmail());
	}
}
