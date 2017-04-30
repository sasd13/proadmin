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
import com.sasd13.proadmin.backend.dao.IStudentTeamDAO;
import com.sasd13.proadmin.backend.model.StudentTeam;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class StudentTeamDAO extends AbstractDAO implements IStudentTeamDAO, IConditionnal {

	public StudentTeamDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<StudentTeam> create(List<StudentTeam> studentTeams) {
		for (StudentTeam studentTeam : studentTeams) {
			currentSession().save(studentTeam);
		}

		currentSession().flush();

		return studentTeams;
	}

	@Override
	public void delete(List<StudentTeam> studentTeams) {
		for (StudentTeam studentTeam : studentTeams) {
			currentSession().remove(studentTeam);
		}

		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from StudentTeam sttm");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<StudentTeam>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return "sttm.student.intermediary = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return "sttm.team.number = ?";
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
