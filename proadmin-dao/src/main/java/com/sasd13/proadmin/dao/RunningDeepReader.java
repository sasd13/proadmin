package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningDeepReader extends DeepReader<Running> {

	private ITeacherDAO teacherDAO;
	private IProjectDAO projectDAO;
	private Map<String, String[]> parameters;

	public RunningDeepReader(IRunningDAO runningDAO, ITeacherDAO teacherDAO, IProjectDAO projectDAO) {
		super(runningDAO);

		this.teacherDAO = teacherDAO;
		this.projectDAO = projectDAO;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieveData(Running running) {
		retrieveDatatProject(running);
		retrieveDataTeacher(running);
	}

	private void retrieveDatatProject(Running running) {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { running.getProject().getCode() });

		Project project = projectDAO.select(parameters).get(0);
		running.setProject(project);
	}

	private void retrieveDataTeacher(Running running) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { running.getTeacher().getNumber() });

		Teacher teacher = teacherDAO.select(parameters).get(0);
		running.setTeacher(teacher);
	}
}
