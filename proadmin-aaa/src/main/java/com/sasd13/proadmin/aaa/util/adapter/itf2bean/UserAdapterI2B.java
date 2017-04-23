package com.sasd13.proadmin.aaa.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.bean.EnumStatus;
import com.sasd13.proadmin.aaa.bean.User;
import com.sasd13.proadmin.itf.bean.user.UserBean;

public class UserAdapterI2B implements IAdapter<UserBean, User> {

	@Override
	public User adapt(UserBean s) {
		User t = new User();

		t.setUserID(s.getId().getId());
		t.setStatus(EnumStatus.find(s.getCoreInfo().getStatus()));
		t.setRoles(s.getCoreInfo().getRoles());
		t.setIntermediary(s.getCoreInfo().getIntermediary());
		t.setEmail(s.getCoreInfo().getEmail());

		return t;
	}
}
