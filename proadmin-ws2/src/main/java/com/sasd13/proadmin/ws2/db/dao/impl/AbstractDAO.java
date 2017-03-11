package com.sasd13.proadmin.ws2.db.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDAO {

	private SessionFactory connectionFactory;

	public AbstractDAO(SessionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	protected Session currentSession() {
		return connectionFactory.getCurrentSession();
	}
}
