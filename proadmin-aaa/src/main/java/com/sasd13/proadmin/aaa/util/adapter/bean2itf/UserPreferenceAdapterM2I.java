package com.sasd13.proadmin.aaa.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.UserPreference;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

public class UserPreferenceAdapterM2I implements IAdapter<UserPreference, UserPreferenceBean> {

	@Override
	public UserPreferenceBean adapt(UserPreference s) {
		UserPreferenceBean t = new UserPreferenceBean();

		t.setId(String.valueOf(s.getId()));
		t.setValue(s.getValue());
		t.setCategory(s.getPreference().getCategory());
		t.setName(s.getPreference().getName());
		t.setDefaultValue(s.getPreference().getDefaultValue());

		return t;
	}
}
