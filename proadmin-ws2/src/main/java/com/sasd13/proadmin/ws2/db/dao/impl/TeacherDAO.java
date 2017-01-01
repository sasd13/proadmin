package com.sasd13.proadmin.ws2.db.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.dao.hibernate.HibernateSession;
import com.sasd13.javaex.dao.hibernate.HibernateUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.ITeacherDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherDAO extends HibernateSession<Teacher> implements ITeacherDAO {

	public TeacherDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public long insert(Teacher teacher) {
		TeacherDTO teacherDTO = new TeacherDTO(teacher);

		HibernateUtils.insert(this, teacherDTO);

		return teacherDTO.getId();
	}

	@Override
	public void update(IUpdateWrapper<Teacher> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(", " + COLUMN_FIRSTNAME + " = ?");
		builder.append(", " + COLUMN_LASTNAME + " = ?");
		builder.append(", " + COLUMN_EMAIL + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Teacher teacher) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		HibernateUtils.delete(this, builder.toString(), teacher);
	}

	@Override
	public Teacher select(long id) {
		return HibernateUtils.select(this, TABLE, COLUMN_ID, id);
	}

	@Override
	public List<Teacher> select(Map<String, String[]> parameters) {
		return HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Teacher> selectAll() {
		return HibernateUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Teacher teacher) {
		return false;
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_CODE;
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_FIRSTNAME;
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_LASTNAME;
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_EMAIL;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<Teacher> updateWrapper) {
		query.setParameter(0, updateWrapper.getWrapped().getNumber());
		query.setParameter(1, updateWrapper.getWrapped().getFirstName());
		query.setParameter(2, updateWrapper.getWrapped().getLastName());
		query.setParameter(3, updateWrapper.getWrapped().getEmail());
		query.setParameter(4, ((TeacherUpdateWrapper) updateWrapper).getNumber());
	}

	@Override
	public void editQueryForDelete(Query query, Teacher teacher) {
		query.setParameter(0, teacher.getNumber());
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

	@Override
	public Teacher getResultValues(Serializable serializable) {
		Teacher teacher = new Teacher();

		teacher.setNumber(((TeacherDTO) serializable).getCode());
		teacher.setFirstName(((TeacherDTO) serializable).getFirstName());
		teacher.setLastName(((TeacherDTO) serializable).getLastName());
		teacher.setEmail(((TeacherDTO) serializable).getEmail());

		return teacher;
	}
}
