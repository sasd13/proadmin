package com.sasd13.proadmin.aaa.util.adapter.itf2bean;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.bean.EnumStatus;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.model.UserPreference;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;

public class UserUpdateAdapterI2B implements IAdapter<UserUpdateBean, User> {

	private static final String DELIMITER = ";";

	private UserPreferenceAdapterI2B userPreferenceAdapter;

	public UserUpdateAdapterI2B() {
		userPreferenceAdapter = new UserPreferenceAdapterI2B();
	}

	@Override
	public User adapt(UserUpdateBean s) {
		User t = new User();

		t.setId(Integer.valueOf(s.getUser().getId().getId()));
		t.setUserID(s.getUser().getCoreInfo().getUserID());
		t.setStatus(EnumStatus.find(s.getUser().getCoreInfo().getStatus()));
		t.setRoles(String.join(DELIMITER, s.getUser().getCoreInfo().getRoles()));
		t.setIntermediary(s.getUser().getCoreInfo().getIntermediary());
		t.setEmail(s.getUser().getCoreInfo().getEmail());

		List<UserPreference> userPreferences = new ArrayList<>();
		t.setUserPreferences(userPreferences);

		for (UserPreferenceBean userPreferenceBean : s.getUser().getLinkedPreferences()) {
			userPreferences.add(userPreferenceAdapter.adapt(userPreferenceBean));
		}

		return t;
	}
}
