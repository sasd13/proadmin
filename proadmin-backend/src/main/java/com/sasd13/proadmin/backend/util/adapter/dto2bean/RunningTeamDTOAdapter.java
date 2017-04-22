package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.dao.dto.RunningTeamDTO;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class RunningTeamDTOAdapter implements IAdapter<RunningTeamDTO, RunningTeam> {

	private RunningDTOAdapter runningDTOAdapter;
	private TeamDTOAdapter teamDTOAdapter;
	private AcademicLevelAdapterD2B academicLevelAdapterD2B;

	public RunningTeamDTOAdapter() {
		runningDTOAdapter = new RunningDTOAdapter();
		teamDTOAdapter = new TeamDTOAdapter();
		academicLevelAdapterD2B = new AcademicLevelAdapterD2B();
	}

	@Override
	public RunningTeam adapt(RunningTeamDTO source) {
		RunningTeam target = new RunningTeam();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(RunningTeamDTO source, RunningTeam target) {
		target.setRunning(runningDTOAdapter.adapt(source.getRunning()));
		target.setTeam(teamDTOAdapter.adapt(source.getTeam()));
		target.setAcademicLevel(academicLevelAdapterD2B.adapt(source.getAcademicLevel()));
	}
}
