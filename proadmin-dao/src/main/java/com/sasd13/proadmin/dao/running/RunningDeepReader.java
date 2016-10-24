package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.member.ITeacherDAO;
import com.sasd13.proadmin.dao.project.IProjectDAO;

public class RunningDeepReader extends DeepReader<Running> {

	private ITeacherDAO iTeacherDAO;
	private IProjectDAO iProjectDAO;

	public RunningDeepReader(IEntityDAO<Running> entityDAO, ITeacherDAO iTeacherDAO, IProjectDAO iProjectDAO) {
		super(entityDAO);

		this.iTeacherDAO = iTeacherDAO;
		this.iProjectDAO = iProjectDAO;
	}

	@Override
	protected void retrieveData(Running running) throws DAOException {
		Teacher teacher = iTeacherDAO.select(running.getTeacher().getId());
		running.setTeacher(teacher);

		Project project = iProjectDAO.select(running.getProject().getId());
		running.setProject(project);
	}
}
