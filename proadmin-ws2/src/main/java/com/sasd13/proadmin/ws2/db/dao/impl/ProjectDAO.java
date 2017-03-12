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
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IProjectDAO;
import com.sasd13.proadmin.ws2.db.dto.ProjectDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectDAO extends AbstractDAO implements IProjectDAO, IConditionnal {

	public ProjectDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public ProjectDTO create(Project project) {
		ProjectDTO dto = new ProjectDTO(project);

		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	@Override
	public void update(IProjectUpdateWrapper updateWrapper) {
		ProjectDTO dto = read(updateWrapper.getCode());

		dto.setDateCreation(updateWrapper.getWrapped().getDateCreation());
		dto.setTitle(updateWrapper.getWrapped().getTitle());
		dto.setDescription(updateWrapper.getWrapped().getDescription());
		currentSession().update(dto);
		currentSession().flush();
	}

	private ProjectDTO read(String code) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.CODE.getName(), new String[] { code });

		return read(parameters).get(0);
	}

	@Override
	public void delete(Project project) {
		ProjectDTO dto = read(project.getCode());

		currentSession().remove(dto);
		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from projects p");

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
			return "p.code";
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
