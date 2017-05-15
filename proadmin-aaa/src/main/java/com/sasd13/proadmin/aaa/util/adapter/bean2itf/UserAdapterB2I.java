package com.sasd13.proadmin.aaa.util.adapter.bean2itf;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.model.UserPreference;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.user.CoreInfo;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.UserPreferenceBean;

public class UserAdapterB2I implements IAdapter<User, UserBean> {

	private static final String DELIMITER = ";";

	@Override
	public UserBean adapt(User s) {
		UserBean t = new UserBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setUserID(s.getUserID());
		coreInfo.setStatus(s.getStatus().getCode());
		coreInfo.setRoles(s.getRoles().split(DELIMITER));
		coreInfo.setIntermediary(s.getIntermediary());
		coreInfo.setEmail(s.getEmail());
		t.setCoreInfo(coreInfo);

		List<UserPreferenceBean> linkedPreferences = new ArrayList<>();
		t.setLinkedPreferences(linkedPreferences);

		UserPreferenceBean linkedPreference;
		for (UserPreference userPreference : s.getUserPreferences()) {
			linkedPreference = new UserPreferenceBean();

			linkedPreference.setId(String.valueOf(userPreference.getId()));
			linkedPreference.setValue(userPreference.getValue());
			linkedPreference.setCategory(userPreference.getPreference().getCategory());
			linkedPreference.setName(userPreference.getPreference().getName());
			linkedPreference.setDefaultValue(userPreference.getPreference().getDefaultValue());
			linkedPreferences.add(linkedPreference);
		}

		return t;
	}
}
