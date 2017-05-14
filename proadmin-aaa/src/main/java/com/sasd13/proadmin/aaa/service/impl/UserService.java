package com.sasd13.proadmin.aaa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.EnumPreferenceCategory;
import com.sasd13.proadmin.aaa.dao.IPreferenceDAO;
import com.sasd13.proadmin.aaa.dao.IUserDAO;
import com.sasd13.proadmin.aaa.model.Preference;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.model.UserPreference;
import com.sasd13.proadmin.aaa.service.IUserService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IPreferenceDAO preferenceDAO;

	@Override
	public User create(User user) {
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

		return userDAO.create(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public User find(Credential credential) {
		return userDAO.find(credential);
	}

	@Override
	public User find(String userID) {
		return userDAO.find(userID);
	}

	@Override
	public List<User> read(Map<String, Object> criterias) {
		return userDAO.read(criterias);
	}
}
