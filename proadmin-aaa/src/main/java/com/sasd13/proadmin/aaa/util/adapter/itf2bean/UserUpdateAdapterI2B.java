package com.sasd13.proadmin.aaa.util.adapter.itf2bean;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.bean.EnumStatus;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.model.UserPreference;
import com.sasd13.proadmin.aaa.model.UserUpdate;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;

public class UserUpdateAdapterI2B implements IAdapter<UserUpdateBean, UserUpdate> {

	private static final String DELIMITER = ";";

	private UserPreferenceAdapterI2B userPreferenceAdapter;
	private CredentialUpdateAdapterI2B credentialUpdateAdapter;

	public UserUpdateAdapterI2B() {
		userPreferenceAdapter = new UserPreferenceAdapterI2B();
		credentialUpdateAdapter = new CredentialUpdateAdapterI2B();
	}

	@Override
	public UserUpdate adapt(UserUpdateBean s) {
		UserUpdate t = new UserUpdate();

		User user = new User();
		user.setId(Long.valueOf(s.getUser().getId().getId()));
		t.setUser(user);

		user.setUserID(s.getUser().getCoreInfo().getUserID());
		user.setStatus(EnumStatus.find(s.getUser().getCoreInfo().getStatus()));
		user.setRoles(String.join(DELIMITER, s.getUser().getCoreInfo().getRoles()));
		user.setIntermediary(s.getUser().getCoreInfo().getIntermediary());
		user.setEmail(s.getUser().getCoreInfo().getEmail());

		if (s.getUser().getLinkedPreferences() != null) {
			List<UserPreference> userPreferences = new ArrayList<>();
			user.setUserPreferences(userPreferences);

			for (UserPreferenceBean userPreferenceBean : s.getUser().getLinkedPreferences()) {
				userPreferences.add(userPreferenceAdapter.adapt(userPreferenceBean));
			}
		}

		if (s.getCredentials() != null) {
			t.setCredentials(credentialUpdateAdapter.adapt(s.getCredentials()));
		}

		return t;
	}
}
