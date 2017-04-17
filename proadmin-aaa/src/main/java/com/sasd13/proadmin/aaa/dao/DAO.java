package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.IDAO;

public abstract class DAO implements IDAO {

	protected IUserDAO userDAO;

	protected DAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public Object getSession(Class<?> mClass) {
		if (IUserDAO.class.isAssignableFrom(mClass)) {
			return userDAO;
		} else {
			return null;
		}
	}
}
