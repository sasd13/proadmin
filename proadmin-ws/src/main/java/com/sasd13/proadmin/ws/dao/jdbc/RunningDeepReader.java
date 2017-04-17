package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.IProjectDAO;
import com.sasd13.proadmin.ws.dao.IRunningDAO;
import com.sasd13.proadmin.ws.dao.ITeacherDAO;

public class RunningDeepReader extends DeepReader<Running> {

	private IProjectDAO projectDAO;
	private ITeacherDAO teacherDAO;
	private Map<String, String[]> parameters;

	public RunningDeepReader(IRunningDAO runningDAO, IProjectDAO projectDAO, ITeacherDAO teacherDAO) {
		super(runningDAO);

		this.projectDAO = projectDAO;
		this.teacherDAO = teacherDAO;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieve(Running running) {
		retrieveDataProject(running);
		retrieveDataTeacher(running);
	}

	private void retrieveDataProject(Running running) {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { running.getProject().getCode() });

		Project project = projectDAO.read(parameters).get(0);
		running.setProject(project);
	}

	private void retrieveDataTeacher(Running running) {
		parameters.clear();
		parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[] { running.getTeacher().getIntermediary() });

		Teacher teacher = teacherDAO.read(parameters).get(0);
		running.setTeacher(teacher);
	}
}
