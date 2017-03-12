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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IProjectDAO;
import com.sasd13.proadmin.ws2.db.dao.IRunningDAO;
import com.sasd13.proadmin.ws2.db.dao.ITeacherDAO;
import com.sasd13.proadmin.ws2.db.dto.ProjectDTO;
import com.sasd13.proadmin.ws2.db.dto.RunningDTO;
import com.sasd13.proadmin.ws2.db.dto.TeacherDTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class RunningDAO extends AbstractDAO implements IRunningDAO, IConditionnal {

	@Autowired
	private IProjectDAO projectDAO;

	@Autowired
	private ITeacherDAO teacherDAO;

	public RunningDAO(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public RunningDTO create(Running running) {
		RunningDTO dto = new RunningDTO(running);

		dto.setProject(readProject(running.getProject().getCode()));
		dto.setTeacher(readTeacher(running.getTeacher().getNumber()));
		currentSession().save(dto);
		currentSession().flush();

		return dto;
	}

	private ProjectDTO readProject(String projectCode) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.CODE.getName(), new String[] { projectCode });

		return projectDAO.read(parameters).get(0);
	}

	private TeacherDTO readTeacher(String teacherNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.NUMBER.getName(), new String[] { teacherNumber });

		return teacherDAO.read(parameters).get(0);
	}

	@Override
	public void update(List<RunningUpdateWrapper> updateWrappers) {
		RunningUpdateWrapper updateWrapper;
		RunningDTO dto;

		for (int i = 0; i < updateWrappers.size(); i++) {
			updateWrapper = updateWrappers.get(i);
			dto = read(updateWrapper.getYear(), updateWrapper.getProjectCode(), updateWrapper.getTeacherNumber());

			dto.setYear(updateWrapper.getWrapped().getYear());
			dto.setProject(readProject(updateWrapper.getWrapped().getProject().getCode()));
			dto.setTeacher(readTeacher(updateWrapper.getWrapped().getTeacher().getNumber()));
			currentSession().update(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	private RunningDTO read(int year, String projectCode, String teacherNumber) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(year) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { projectCode });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { teacherNumber });

		return read(parameters).get(0);
	}

	@Override
	public void delete(List<Running> runnings) {
		Running running;
		RunningDTO dto;

		for (int i = 0; i < runnings.size(); i++) {
			running = runnings.get(i);
			dto = read(running.getYear(), running.getProject().getCode(), running.getTeacher().getNumber());

			currentSession().remove(dto);

			if (i % 100 == 0) {
				currentSession().flush();
			}
		}

		currentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RunningDTO> read(Map<String, String[]> parameters) {
		StringBuilder builder = new StringBuilder();
		builder.append("from runnings r");

		if (!parameters.isEmpty()) {
			appendWhere(parameters, builder, this);
		}

		Query query = currentSession().createQuery(builder.toString());

		if (!parameters.isEmpty()) {
			resolveWhere(parameters, query);
		}

		return (List<RunningDTO>) query.getResultList();
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return "r.year";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return "r.project.code";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return "r.teacher.number";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editQueryForSelect(Query query, int index, String key, String value) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			try {
				query.setParameter(index, Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			query.setParameter(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
