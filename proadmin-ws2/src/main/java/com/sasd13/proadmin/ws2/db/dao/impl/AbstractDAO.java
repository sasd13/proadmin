package com.sasd13.proadmin.ws2.db.dao.impl;

import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sasd13.javaex.util.condition.ConditionBuilder;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;

public abstract class AbstractDAO {

	private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class);

	private SessionFactory sessionFactory;

	public AbstractDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected String appendWhere(Map<String, String[]> parameters, StringBuilder builder, IConditionnal conditionnal) {
		builder.append(" where ");

		try {
			builder.append(ConditionBuilder.build(parameters, conditionnal));
		} catch (ConditionException e) {
			LOGGER.error(e);
			throw new HibernateException(e);
		}

		return builder.toString();
	}

	protected void resolveWhere(Map<String, String[]> parameters, Query query) {
		int i = 0;

		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				try {
					editQueryForSelect(query, i, entry.getKey(), value);
				} catch (ConditionException e) {
					LOGGER.error(e);
					throw new HibernateException(e);
				}
			}
		}
	}

	protected void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		// Do nothing
	}
}
