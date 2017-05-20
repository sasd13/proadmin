package com.sasd13.proadmin.aaa.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.UserPreference;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

public class UserPreferenceAdapterI2B implements IAdapter<UserPreferenceBean, UserPreference> {

	@Override
	public UserPreference adapt(UserPreferenceBean s) {
		UserPreference t = new UserPreference();

		t.setId(Integer.valueOf(s.getId()));
		t.setValue(s.getValue());

		return t;
	}
}
