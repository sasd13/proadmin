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
import com.sasd13.javaex.util.order.OrderException;
import com.sasd13.proadmin.backend.dao.IProjectDAO;
import com.sasd13.proadmin.backend.model.Project;
import com.sasd13.proadmin.util.EnumCriteria;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectDAO extends AbstractDAO implements IProjectDAO {

	public ProjectDAO(@Qualifier("mSessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Project create(Project project) {
		currentSession().save(project);
		currentSession().flush();

		return project;
	}

	@Override
	public void update(Project project) {
		currentSession().update(project);
		currentSession().flush();
	}

	@Override
	public void delete(Project project) {
		currentSession().remove(project);
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> read(Map<String, Object> criterias) {
		StringBuilder builder = new StringBuilder();
		builder.append("from Project pr");

		if (!criterias.isEmpty()) {
			appendCriterias(criterias, builder);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!criterias.isEmpty()) {
			resolveCriterias(criterias, query);
		}

		return (List<Project>) query.getResultList();
	}

	@Override
	public String getCondition(String key) {
		if (EnumCriteria.CODE.getCode().equalsIgnoreCase(key)) {
			return "pr.code = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public String getOrdered(String key) {
		if (EnumCriteria.CODE.getCode().equalsIgnoreCase(key)) {
			return "pr.code";
		} else {
			throw new OrderException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) {
		if (EnumCriteria.CODE.getCode().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}
}
