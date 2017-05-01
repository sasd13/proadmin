/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.sasd13.proadmin.backend.dao.IStudentDAO;
import com.sasd13.proadmin.backend.model.Student;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
public class StudentDAO extends AbstractDAO implements IStudentDAO, IConditionnal {

	public StudentDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Student create(Student student) {
		currentSession().save(student);
		currentSession().flush();

		return student;
	}

	@Override
	public void update(Student student) {
		currentSession().update(student);
		currentSession().flush();
	}

	@Override
	public void delete(Student student) {
		currentSession().remove(student);
		currentSession().flush();
	}

	@Override
	public Student read(String intermediary) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Student st where st.intermediary = :intermediary");

		Query query = currentSession().createQuery(builder.toString());
		query.setParameter("intermediary", intermediary);

		return (Student) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Student st");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<Student>) query.getResultList();
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			return "st.intermediary = ?" + index;
		} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(key)) {
			return "st.firstName = ?" + index;
		} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(key)) {
			return "st.lastName = ?" + index;
		} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(key)) {
			return "st.email = ?" + index;
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.INTERMEDIARY.getCode().equalsIgnoreCase(key)) {
			return "st.intermediary";
		} else if (EnumCriteria.FIRSTNAME.getCode().equalsIgnoreCase(key)) {
			return "st.firstName";
		} else if (EnumCriteria.LASTNAME.getCode().equalsIgnoreCase(key)) {
			return "st.lastName";
		} else if (EnumCriteria.EMAIL.getCode().equalsIgnoreCase(key)) {
			return "st.email";
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
