package com.sasd13.proadmin.backend.dao.impl;

import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sasd13.javaex.util.condition.ConditionBuilder;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.javaex.util.order.IOrderable;
import com.sasd13.javaex.util.order.OrderBuilder;
import com.sasd13.proadmin.util.EnumRestriction;

public abstract class AbstractDAO implements IConditionnal, IOrderable {

	private SessionFactory sessionFactory;

	public AbstractDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session currentSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	@SuppressWarnings("unchecked")
	protected void appendCriterias(Map<String, Object> criterias, StringBuilder builder) {
		Object criteria;

		if ((criteria = criterias.get(EnumRestriction.WHERE.getCode())) != null) {
			appendWhere((Map<String, String[]>) criteria, builder);
		}

		if ((criteria = criterias.get(EnumRestriction.ORDER.getCode())) != null) {
			appendOrderBy((Map<String, String>) criteria, builder);
		}
	}

	private void appendWhere(Map<String, String[]> criterias, StringBuilder builder) {
		builder.append(" where ");
		builder.append(ConditionBuilder.build(criterias, this));
	}

	private void appendOrderBy(Map<String, String> criterias, StringBuilder builder) {
		builder.append(" order by ");
		builder.append(OrderBuilder.build(criterias, this));
	}

	@SuppressWarnings("unchecked")
	protected void resolveCriterias(Map<String, Object> criterias, Query query) {
		Object criteria;

		if ((criteria = criterias.get(EnumRestriction.WHERE.getCode())) != null) {
			resolveWhere((Map<String, String[]>) criteria, query);
		}

		if ((criteria = criterias.get(EnumRestriction.LIMIT.getCode())) != null) {
			query.setMaxResults(Integer.valueOf((String) criteria));
		}

		if ((criteria = criterias.get(EnumRestriction.OFFSET.getCode())) != null) {
			query.setFirstResult(Integer.valueOf((String) criteria));
		}
	}

	private void resolveWhere(Map<String, String[]> criterias, Query query) {
		int i = 0;

		for (Map.Entry<String, String[]> entry : criterias.entrySet()) {
			for (String value : entry.getValue()) {
				editQueryForSelect(query, i++, entry.getKey(), value);
			}
		}
	}

	@Override
	public String getCondition(String key) {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public String getOrdered(String key) {
		throw new NotImplementedException("Not implemented");
	}

	protected void editQueryForSelect(Query query, int index, String key, String value) {
		throw new NotImplementedException("Not implemented");
	}
}
