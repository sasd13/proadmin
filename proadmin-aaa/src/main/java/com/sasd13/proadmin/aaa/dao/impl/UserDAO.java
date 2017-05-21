/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.javaex.util.order.OrderException;
import com.sasd13.proadmin.aaa.dao.IUserDAO;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.model.UserPreference;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
public class UserDAO extends AbstractDAO implements IUserDAO, IConditionnal {

	public UserDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User create(User user) {
		currentSession().save(user);
		currentSession().flush();

		return user;
	}

	@Override
	public void update(User user) {
		User userToUpdate = find(user.getUserID());

		for (UserPreference userPreference : user.getUserPreferences()) {
			for (UserPreference userPreferenceToUpdate : userToUpdate.getUserPreferences()) {
				if (userPreferenceToUpdate.getId() == userPreference.getId()) {
					userPreferenceToUpdate.setValue(userPreference.getValue());

					break;
				}
			}
		}

		currentSession().update(user);
		currentSession().flush();
	}

	@Override
	public User find(Credential credential) {
		StringBuilder builder = new StringBuilder();
		builder.append("from User usr where usr.username = :username and usr.password = :password");

		Query query = currentSession().createQuery(builder.toString());
		query.setParameter("username", credential.getUsername());
		query.setParameter("password", credential.getPassword());

		try {
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User find(String userID) {
		StringBuilder builder = new StringBuilder();
		builder.append("from User usr where usr.userID = :userID");

		Query query = currentSession().createQuery(builder.toString());
		query.setParameter("userID", userID);

		try {
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from User usr");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<User>) query.getResultList();
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.USERID.getCode().equalsIgnoreCase(key)) {
			return "usr.userID = ?" + index;
		} else if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			return "usr.intermediary = ?" + index;
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.USERID.getCode().equalsIgnoreCase(key)) {
			return "usr.userID";
		} else if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			return "usr.intermediary";
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.USERID.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
