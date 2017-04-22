package com.sasd13.proadmin.backend.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamAdapterI2B implements IAdapter<RunningTeamBean, RunningTeam> {

	private RunningAdapterI2B runningAdapter;
	private TeamAdapterI2B teamAdapter;
	private AcademicLevelAdapterI2B academicLevelAdapter;

	public RunningTeamAdapterI2B() {
		runningAdapter = new RunningAdapterI2B();
		teamAdapter = new TeamAdapterI2B();
		academicLevelAdapter = new AcademicLevelAdapterI2B();
	}

	@Override
	public RunningTeam adapt(RunningTeamBean s) {
		RunningTeam t = new RunningTeam();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		t.setRunning(runningAdapter.adapt(s.getLinkedRunning()));
		t.setTeam(teamAdapter.adapt(s.getLinkedTeam()));
		t.setAcademicLevel(academicLevelAdapter.adapt(s.getLinkedAcademicLevel()));

		return t;
	}
}
