package com.sasd13.proadmin.backend.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.AcademicLevel;
import com.sasd13.proadmin.backend.bean.Running;
import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.backend.bean.Team;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamAdapterI2B implements IAdapter<RunningTeamBean, RunningTeam> {

	@Override
	public RunningTeam adapt(RunningTeamBean s) {
		RunningTeam t = new RunningTeam();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		Running running = new Running();
		running.setId(Long.valueOf(s.getLinkedRunning().getId()));
		t.setRunning(running);

		Team team = new Team();
		team.setId(Long.valueOf(s.getLinkedTeam().getId()));
		t.setTeam(team);

		AcademicLevel academicLevel = new AcademicLevel();
		academicLevel.setId(Long.valueOf(s.getLinkedAcademicLevel().getId()));
		t.setAcademicLevel(academicLevel);

		return t;
	}
}
