package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.backend.dao.dto.RunningTeamDTO;

public class RunningTeamAdapterD2B implements IAdapter<RunningTeamDTO, RunningTeam> {

	private RunningAdapterD2B runningAdapter;
	private TeamAdapterD2B teamAdapter;
	private AcademicLevelAdapterD2B academicLevelAdapter;

	public RunningTeamAdapterD2B() {
		runningAdapter = new RunningAdapterD2B();
		teamAdapter = new TeamAdapterD2B();
		academicLevelAdapter = new AcademicLevelAdapterD2B();
	}

	@Override
	public RunningTeam adapt(RunningTeamDTO s) {
		RunningTeam t = new RunningTeam();

		t.setId(s.getId());
		t.setRunning(runningAdapter.adapt(s.getRunning()));
		t.setTeam(teamAdapter.adapt(s.getTeam()));
		t.setAcademicLevel(academicLevelAdapter.adapt(s.getAcademicLevel()));

		return t;
	}
}
