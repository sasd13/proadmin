/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws2.db.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.dao.hibernate.HibernateSession;
import com.sasd13.javaex.dao.hibernate.HibernateUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.IProjectDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectDAO extends HibernateSession<Project> implements IProjectDAO {

	@Override
	public long insert(Project project) {
		TeacherDTO teacherDTO = new TeacherDTO(teacher);

		HibernateUtils.insert(this, teacherDTO);

		return teacherDTO.getId();
	}

	@Override
	public void update(IUpdateWrapper<Project> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(", " + COLUMN_DATECREATION + " = ?");
		builder.append(", " + COLUMN_TITLE + " = ?");
		builder.append(", " + COLUMN_DESCRIPTION + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		HibernateUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Project project) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		HibernateUtils.delete(this, builder.toString(), project);
	}

	@Override
	public Project select(long id) {
		return null;
	}

	@Override
	public List<Project> select(Map<String, String[]> parameters) {
		return HibernateUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Project> selectAll() {
		return HibernateUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Project project) {
		return false;
	}

	@Override
	public void editQueryForUpdate(Query query, IUpdateWrapper<Project> updateWrapper) {
		query.setParameter(1, updateWrapper.getWrapped().getCode());
		query.setParameter(2, String.valueOf(updateWrapper.getWrapped().getDateCreation()));
		query.setParameter(3, updateWrapper.getWrapped().getTitle());
		query.setParameter(4, updateWrapper.getWrapped().getDescription());
		query.setParameter(5, ((IProjectUpdateWrapper) updateWrapper).getCode());
	}

	@Override
	public void editQueryForDelete(Query query, Project project) {
		query.setParameter(1, project.getCode());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_CODE;
		} else if (EnumParameter.TITLE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_TITLE;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TITLE.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public Project getResultValues(Serializable serializable) {
		Project project = new Project(resultSet.getString(COLUMN_CODE));

		project.setDateCreation(Timestamp.valueOf(resultSet.getString(COLUMN_DATECREATION)));
		project.setTitle(resultSet.getString(COLUMN_TITLE));
		project.setDescription(resultSet.getString(COLUMN_DESCRIPTION));

		return project;
	}
}
