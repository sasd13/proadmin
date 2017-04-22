package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.backend.dao.dto.RunningTeamDTO;

public class RunningTeamAdapterB2D implements IAdapter<RunningTeam, RunningTeamDTO> {

	private RunningAdapterB2D runningAdapter;
	private TeamAdapterB2D teamAdapter;
	private AcademicLevelAdapterB2D academicLevelAdapter;

	public RunningTeamAdapterB2D() {
		runningAdapter = new RunningAdapterB2D();
		teamAdapter = new TeamAdapterB2D();
		academicLevelAdapter = new AcademicLevelAdapterB2D();
	}

	@Override
	public RunningTeamDTO adapt(RunningTeam s) {
		RunningTeamDTO t = new RunningTeamDTO();

		t.setId(s.getId());
		t.setRunning(runningAdapter.adapt(s.getRunning()));
		t.setTeam(teamAdapter.adapt(s.getTeam()));
		t.setAcademicLevel(academicLevelAdapter.adapt(s.getAcademicLevel()));

		return t;
	}
}
