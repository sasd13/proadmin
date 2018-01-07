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
import com.sasd13.proadmin.backend.dao.IStudentTeamDAO;
import com.sasd13.proadmin.backend.entity.StudentTeam;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
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
	public List<StudentTeam> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from StudentTeam sttm");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<StudentTeam>) query.getResultList();
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			return "sttm.student.intermediary = ?" + index;
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			return "sttm.team.number = ?" + index;
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			return "sttm.student.intermediary";
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			return "sttm.team.number";
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
