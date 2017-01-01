/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws2.db.dao.IStudentTeamDAO;
import com.sasd13.proadmin.ws2.db.dto.StudentTeamDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class StudentTeamDAO extends HibernateSession<StudentTeam> implements IStudentTeamDAO {

	public StudentTeamDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public long insert(StudentTeam studentTeam) {
		StudentTeamDTO dto = new StudentTeamDTO(studentTeam);

		HibernateUtils.insert(this, dto);

		return dto.getId();
	}

	@Override
	public void update(IUpdateWrapper<StudentTeam> updateWrapper) {
		// Do nothing
	}

	@Override
	public void delete(StudentTeam studentTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_STUDENT + " = ?");
		builder.append(" AND " + COLUMN_TEAM + " = ?");

		HibernateUtils.delete(this, builder.toString(), studentTeam);
	}

	@Override
	public Serializable select(long id) {
		return null;
	}

	@Override
	public List<Serializable> select(Map<String, String[]> parameters) {
		return (List<Serializable>) HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Serializable> selectAll() {
		return (List<Serializable>) HibernateUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(StudentTeam studentTeam) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<StudentTeam> updateWrapper) {
		// Do nothing
	}

	@Override
	public void editQueryForDelete(Query query, StudentTeam studentTeam) {
		query.setParameter(1, studentTeam.getStudent().getNumber());
		query.setParameter(2, studentTeam.getTeam().getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_STUDENT;
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_TEAM;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
