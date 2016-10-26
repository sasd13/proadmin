package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.member.ITeacherDAO;
import com.sasd13.proadmin.dao.project.IProjectDAO;
import com.sasd13.proadmin.util.Binder;

public class RunningDeepReader extends DeepReader<Running> {

	private ITeacherDAO teacherDAO;
	private IProjectDAO projectDAO;

	public RunningDeepReader(IEntityDAO<Running> entityDAO, ITeacherDAO teacherDAO, IProjectDAO projectDAO) {
		super(entityDAO);

		this.teacherDAO = teacherDAO;
		this.projectDAO = projectDAO;
	}

	@Override
	protected void retrieveData(Running running) throws DAOException {
		Teacher teacher = teacherDAO.select(running.getTeacher().getId());
		Binder.bind(running.getTeacher(), teacher);

		Project project = projectDAO.select(running.getProject().getId());
		Binder.bind(running.getProject(), project);
	}
}
