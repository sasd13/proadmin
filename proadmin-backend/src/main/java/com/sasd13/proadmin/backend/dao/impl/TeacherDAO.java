package com.sasd13.proadmin.backend.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.backend.dao.ITeacherDAO;
import com.sasd13.proadmin.backend.model.Teacher;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
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
	public List<Teacher> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Teacher tc");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<Teacher>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.INTERMEDIARY.getName().equalsIgnoreCase(key)) {
			return "tc.intermediary = ?";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return "tc.firstName = ?";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return "tc.lastName = ?";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return "tc.email = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.INTERMEDIARY.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
