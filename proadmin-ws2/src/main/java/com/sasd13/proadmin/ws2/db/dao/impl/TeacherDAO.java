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
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ITeacherDAO;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherDAO extends AbstractDAO implements ITeacherDAO, IConditionnal {

	public TeacherDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public TeacherDTO create(Teacher teacher) {
		TeacherDTO dto = new TeacherDTO(teacher);

		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	@Override
	public void update(List<TeacherUpdateWrapper> updateWrappers) {
		TeacherUpdateWrapper updateWrapper;
		TeacherDTO dto;

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

	private TeacherDTO read(String number) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.NUMBER.getName(), new String[] { number });

		return read(parameters).get(0);
	}

	@Override
	public void delete(List<Teacher> teachers) {
		Teacher teacher;
		TeacherDTO dto;

		for (int i = 0; i < teachers.size(); i++) {
			teacher = teachers.get(i);
			dto = read(teacher.getNumber());

			currentSession().remove(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from teachers t");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<TeacherDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return "t.number" + " = ?";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return "t.firstName" + " = ?";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return "t.lastName" + " = ?";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return "t.email" + " = ?";
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
