package com.sasd13.proadmin.core.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.bean.running.handler.RunningHandler;
import com.sasd13.proadmin.core.util.Parameter;

public class RunningDeepReader extends DeepReader<Running> {
	
	private TeacherDAO teacherDAO;
	private ProjectDAO projectDAO;
	private TeamDAO teamDAO;
	
	public RunningDeepReader(IEntityDAO<Running> entityDAO, TeacherDAO teacherDAO, ProjectDAO projectDAO, TeamDAO teamDAO) {
		super(entityDAO);
		
		this.teacherDAO = teacherDAO;
		this.projectDAO = projectDAO;
		this.teamDAO = teamDAO;
	}
	
	@Override
	protected void retrieveData(Running running) {
		Teacher teacher = teacherDAO.select(running.getTeacher().getId());
		running.setTeacher(teacher);
		
		Project project = projectDAO.select(running.getProject().getId());
		running.setProject(project);
		
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put(Parameter.RUNNING.getName(), new String[]{String.valueOf(running.getId())});
		
		List<Team> teams = teamDAO.select(parameters);
		RunningHandler.addTeamsToRunning(teams, running);
	}
}
