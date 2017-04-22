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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.backend.dao.IStudentDAO;
import com.sasd13.proadmin.backend.dao.dto.StudentDTO;
import com.sasd13.proadmin.backend.util.adapter.bean2dto.StudentAdapterB2D;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class StudentDAO extends AbstractDAO implements IStudentDAO, IConditionnal {

	public StudentDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public StudentDTO create(Student student) {
		StudentDTO dto = new StudentAdapterB2D().adapt(student);

		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	@Override
	public void update(Student student) {
		currentSession().update(new StudentAdapterB2D().adapt(student));
		currentSession().flush();
	}

	@Override
	public void delete(Student student) {
		currentSession().remove(new StudentAdapterB2D().adapt(student));
		currentSession().flush();
	}

	@Override
	public StudentDTO read(String intermediary) {
		StringBuilder builder = new StringBuilder();
		builder.append("from students st where st.intermediary = :intermediary");

		Query query = currentSession().createQuery(builder.toString());
		query.setParameter("intermediary", intermediary);

		return (StudentDTO) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from students st");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<StudentDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.INTERMEDIARY.getName().equalsIgnoreCase(key)) {
			return "st.intermediary" + " = ?";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return "st.firstName" + " = ?";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return "st.lastName" + " = ?";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return "st.email" + " = ?";
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
