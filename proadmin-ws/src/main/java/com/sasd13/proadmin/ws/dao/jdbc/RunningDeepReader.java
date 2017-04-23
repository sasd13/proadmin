package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.IProjectDAO;
import com.sasd13.proadmin.ws.dao.IRunningDAO;
import com.sasd13.proadmin.ws.dao.ITeacherDAO;

public class RunningDeepReader extends DeepReader<IRunning> {

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
	protected void retrieve(IRunning iRunning) {
		retrieveDataProject(iRunning);
		retrieveDataTeacher(iRunning);
	}

	private void retrieveDataProject(IRunning iRunning) {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { iRunning.getProject().getCode() });

		IProject iProject = projectDAO.read(parameters).get(0);
		iRunning.setProject(iProject);
	}

	private void retrieveDataTeacher(IRunning iRunning) {
		parameters.clear();
		parameters.put(EnumParameter.INTERMEDIARY.getName(), new String[] { iRunning.getTeacher().getIntermediary() });

		ITeacher iTeacher = teacherDAO.read(parameters).get(0);
		iRunning.setTeacher(iTeacher);
	}
}
