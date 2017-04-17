package com.sasd13.proadmin.aaa.util.adapter.b2m;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.user.EnumStatus;
import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.itf.bean.user.UserBean;

public class UserBeanToUserAdapter implements IAdapter<UserBean, User> {

	@Override
	public User adapt(UserBean source) {
		User target = new User();

		adapt(source, target);

		return target;
	}

	public void adapt(UserBean source, User target) {
		target.setUserID(source.getUserID());
		target.setStatus(EnumStatus.find(source.getStatus()));
		target.setRoles(source.getRoles());
		target.setIntermediary(source.getIntermediary());
		target.setEmail(source.getEmail());
	};
}
