package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.dao.IProjectDAO;
import com.sasd13.proadmin.ws.dao.IRunningDAO;
import com.sasd13.proadmin.ws.dao.ITeacherDAO;

public class RunningDeepReader extends DeepReader<Running> {

	private IProjectDAO projectDAO;
	private ITeacherDAO teacherDAO;
	private Map<String, String[]> criterias;

	public RunningDeepReader(IRunningDAO runningDAO, IProjectDAO projectDAO, ITeacherDAO teacherDAO) {
		super(runningDAO);

		this.projectDAO = projectDAO;
		this.teacherDAO = teacherDAO;
		criterias = new HashMap<>();
	}

	@Override
	protected void retrieve(Running running) {
		retrieveDataProject(running);
		retrieveDataTeacher(running);
	}

	private void retrieveDataProject(Running running) {
		criterias.clear();
		criterias.put(EnumCriteria.CODE.getCode(), new String[] { running.getProject().getCode() });

		Project project = projectDAO.read(criterias).get(0);
		running.setProject(project);
	}

	private void retrieveDataTeacher(Running running) {
		criterias.clear();
		criterias.put(EnumCriteria.INTERMEDIARY.getCode(), new String[] { running.getTeacher().getIntermediary() });

		Teacher teacher = teacherDAO.read(criterias).get(0);
		running.setTeacher(teacher);
	}
}
