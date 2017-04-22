package com.sasd13.proadmin.aaa.util.adapter.model2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.itf.bean.user.UserBean;

public class UserAdapterM2B implements IAdapter<User, UserBean> {

	@Override
	public UserBean adapt(User s) {
		UserBean t = new UserBean();

		t.setUserID(s.getUserID());
		t.setStatus(s.getStatus().getCode());
		t.setRoles(s.getRoles());
		t.setIntermediary(s.getIntermediary());
		t.setEmail(s.getEmail());

		return t;
	}
}
