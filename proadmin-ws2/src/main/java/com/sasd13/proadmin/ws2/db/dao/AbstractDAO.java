package com.sasd13.proadmin.ws2.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractDAO {

	protected SessionFactory sessionFactory;

	public AbstractDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
}
