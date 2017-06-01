package com.sasd13.proadmin.aaa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.aaa.dao.IPreferenceDAO;
import com.sasd13.proadmin.aaa.dao.IUserDAO;
import com.sasd13.proadmin.aaa.model.Preference;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.model.UserPreference;
import com.sasd13.proadmin.aaa.model.UserUpdate;
import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.util.adapter.bean2itf.UserAdapterM2I;
import com.sasd13.proadmin.aaa.util.adapter.itf2bean.UserCreateAdapterI2B;
import com.sasd13.proadmin.aaa.util.adapter.itf2bean.UserUpdateAdapterI2B;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.create.UserCreateBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;
import com.sasd13.proadmin.util.EnumPreferenceCategory;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IPreferenceDAO preferenceDAO;

	@Override
	public UserBean create(UserCreateBean userCreateBean) {
		User user = new UserCreateAdapterI2B().adapt(userCreateBean);
		List<Preference> preferences = preferenceDAO.findByCategory(EnumPreferenceCategory.GENERAL.getCode());
		List<UserPreference> userPreferences = new ArrayList<>();
		UserPreference userPreference;

		for (Preference preference : preferences) {
			userPreference = new UserPreference();

			userPreference.setValue(preference.getDefaultValue());
			userPreference.setUser(user);
			userPreference.setPreference(preference);
			userPreferences.add(userPreference);
		}

		user.setUserPreferences(userPreferences);
		user.setUserID(UUID.randomUUID().toString());

		user = userDAO.create(user);

		return new UserAdapterM2I().adapt(user);
	}

	@Override
	public void update(UserUpdateBean userUpdateBean) {
		UserUpdate userUpdate = new UserUpdateAdapterI2B().adapt(userUpdateBean);

		userDAO.update(userUpdate);
	}

	@Override
	public UserBean find(String userID) {
		User user = userDAO.find(userID);

		return user != null ? new UserAdapterM2I().adapt(user) : null;
	}

	@Override
	public List<UserBean> read(Map<String, Object> criterias) {
		List<User> users = userDAO.read(criterias);

		return adaptM2I(users);
	}

	private List<UserBean> adaptM2I(List<User> users) {
		List<UserBean> list = new ArrayList<>();
		UserAdapterM2I adapter = new UserAdapterM2I();

		for (User user : users) {
			list.add(adapter.adapt(user));
		}

		return list;
	}
}
