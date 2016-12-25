package com.sasd13.proadmin.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.IProjectDAO;
import com.sasd13.proadmin.dao.IRunningDAO;
import com.sasd13.proadmin.dao.ITeacherDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningDeepReader extends DeepReader<Running> {

	private ITeacherDAO teacherDAO;
	private IProjectDAO projectDAO;

	public RunningDeepReader(IRunningDAO runningDAO, ITeacherDAO teacherDAO, IProjectDAO projectDAO) {
		super(runningDAO);

		this.teacherDAO = teacherDAO;
		this.projectDAO = projectDAO;
	}

	@Override
	protected void retrieveData(Running running) throws DAOException {
		Map<String, String[]> parameters = new HashMap<>();

		retrieveDatatProject(running, parameters);
		retrieveDataTeacher(running, parameters);
	}

	private void retrieveDatatProject(Running running, Map<String, String[]> parameters) throws DAOException {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { running.getProject().getCode() });

		Project project = projectDAO.select(parameters).get(0);
		running.setProject(project);
	}

	private void retrieveDataTeacher(Running running, Map<String, String[]> parameters) throws DAOException {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { running.getTeacher().getNumber() });

		Teacher teacher = teacherDAO.select(parameters).get(0);
		running.setTeacher(teacher);
	}
}
