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
import com.sasd13.proadmin.backend.bean.Project;
import com.sasd13.proadmin.backend.dao.IProjectDAO;
import com.sasd13.proadmin.backend.dao.dto.ProjectDTO;
import com.sasd13.proadmin.backend.util.adapter.bean2dto.ProjectAdapterB2D;
import com.sasd13.proadmin.util.EnumParameter;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectDAO extends AbstractDAO implements IProjectDAO, IConditionnal {

	public ProjectDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public ProjectDTO create(Project project) {
		ProjectDTO dto = new ProjectAdapterB2D().adapt(project);

		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	@Override
	public void update(Project project) {
		currentSession().update(new ProjectAdapterB2D().adapt(project));
		currentSession().flush();
	}

	@Override
	public void delete(Project project) {
		currentSession().remove(new ProjectAdapterB2D().adapt(project));
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from projects pr");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<ProjectDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return "pr.code = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
