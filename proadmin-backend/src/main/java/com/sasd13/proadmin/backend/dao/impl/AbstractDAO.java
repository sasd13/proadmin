package com.sasd13.proadmin.backend.dao.impl;

import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sasd13.javaex.util.condition.ConditionBuilder;
import com.sasd13.javaex.util.condition.IConditionnal;

public abstract class AbstractDAO {

	private SessionFactory sessionFactory;

	public AbstractDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected String appendWhere(Map<String, String[]> parameters, StringBuilder builder, IConditionnal conditionnal) {
		builder.append(" where ");
		builder.append(ConditionBuilder.build(parameters, conditionnal));

		return builder.toString();
	}

	protected void resolveWhere(Map<String, String[]> parameters, Query query) {
		int i = 0;

		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				editQueryForSelect(query, i++, entry.getKey(), value);
			}
		}
	}

	protected void editQueryForSelect(Query query, int index, String key, String value) {
		throw new NotImplementedException("Not implemented");
	}
}
