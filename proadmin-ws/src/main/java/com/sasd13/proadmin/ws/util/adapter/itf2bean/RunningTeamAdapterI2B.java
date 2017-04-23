package com.sasd13.proadmin.ws.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;
import com.sasd13.proadmin.ws.bean.AcademicLevel;
import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.RunningTeam;
import com.sasd13.proadmin.ws.bean.Teacher;
import com.sasd13.proadmin.ws.bean.Team;

public class RunningTeamAdapterI2B implements IAdapter<RunningTeamBean, RunningTeam> {

	@Override
	public RunningTeam adapt(RunningTeamBean s) {
		RunningTeam t = new RunningTeam();

		Running running = new Running();
		running.setYear(s.getLinkedRunning().getYearStarted());
		t.setRunning(running);

		Project project = new Project();
		project.setCode(s.getLinkedRunning().getLinkedProject().getCode());
		running.setProject(project);

		Teacher teacher = new Teacher();
		teacher.setIntermediary(s.getLinkedRunning().getLinkedTeacher().getIntermediary());
		running.setTeacher(teacher);

		Team team = new Team();
		team.setNumber(s.getLinkedTeam().getNumber());
		t.setTeam(team);

		AcademicLevel academicLevel = new AcademicLevel();
		academicLevel.setCode(s.getLinkedAcademicLevel().getCode());
		t.setAcademicLevel(academicLevel);

		return t;
	}
}
