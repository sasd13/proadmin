/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.util.HashMap;
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
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.StudentUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IStudentDAO;
import com.sasd13.proadmin.ws2.db.dto.StudentDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class StudentDAO extends AbstractDAO implements IStudentDAO, IConditionnal {

	public StudentDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public StudentDTO create(Student student) {
		StudentDTO dto = new StudentDTO(student);

		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	@Override
	public void update(List<StudentUpdateWrapper> updateWrappers) {
		StudentUpdateWrapper updateWrapper;
		StudentDTO dto;

		for (int i = 0; i < updateWrappers.size(); i++) {
			updateWrapper = updateWrappers.get(i);
			dto = read(updateWrapper.getNumber());

			dto.setFirstName(updateWrapper.getWrapped().getFirstName());
			dto.setLastName(updateWrapper.getWrapped().getLastName());
			dto.setEmail(updateWrapper.getWrapped().getEmail());
			currentSession().update(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	private StudentDTO read(String number) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.NUMBER.getName(), new String[] { number });

		return read(parameters).get(0);
	}

	@Override
	public void delete(List<Student> students) {
		Student student;
		StudentDTO dto;

		for (int i = 0; i < students.size(); i++) {
			student = students.get(i);
			dto = read(student.getNumber());

			currentSession().remove(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from students s");

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
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return "s.number" + " = ?";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return "s.firstName" + " = ?";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return "s.lastName" + " = ?";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return "s.email" + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
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
