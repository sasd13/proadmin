package com.sasd13.proadmin.dao;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;

public class RunningDeepReader extends DeepReader<Running> {
	
	private TeacherDAO teacherDAO;
	private ProjectDAO projectDAO;
	
	public RunningDeepReader(IEntityDAO<Running> entityDAO, TeacherDAO teacherDAO, ProjectDAO projectDAO) {
		super(entityDAO);
		
		this.teacherDAO = teacherDAO;
		this.projectDAO = projectDAO;
	}
	
	@Override
	protected void retrieveData(Running running) {
		Teacher teacher = teacherDAO.select(running.getTeacher().getId());
		running.setTeacher(teacher);
		
		Project project = projectDAO.select(running.getProject().getId());
		running.setProject(project);
	}
}
