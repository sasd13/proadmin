package com.sasd13.proadmin.backend.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.javaex.util.order.OrderException;
import com.sasd13.proadmin.backend.dao.ITeacherDAO;
import com.sasd13.proadmin.backend.model.Teacher;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
public class TeacherDAO extends AbstractDAO implements ITeacherDAO, IConditionnal {

	public TeacherDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Teacher create(Teacher teacher) {
		currentSession().save(teacher);
		currentSession().flush();

		return teacher;
	}

	@Override
	public void update(Teacher teacher) {
		currentSession().update(teacher);
		currentSession().flush();
	}

	@Override
	public void delete(Teacher teacher) {
		currentSession().remove(teacher);
		currentSession().flush();
	}

	@Override
	public Teacher read(String intermediary) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Teacher tc where tc.intermediary = :intermediary");

		Query query = currentSession().createQuery(builder.toString());
		query.setParameter("intermediary", intermediary);

		return (Teacher) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Teacher tc");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<Teacher>) query.getResultList();
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			return "tc.intermediary = ?" + index;
		} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(key)) {
			return "tc.firstName = ?" + index;
		} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(key)) {
			return "tc.lastName = ?" + index;
		} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(key)) {
			return "tc.email = ?" + index;
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			return "tc.intermediary";
		} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(key)) {
			return "tc.firstName";
		} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(key)) {
			return "tc.lastName";
		} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(key)) {
			return "tc.email";
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
