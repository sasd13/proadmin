package com.sasd13.proadmin.ws2.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.ws2.dao.dto.RunningTeamDTO;

public class RunningTeamDTOAdapter implements IAdapter<RunningTeamDTO, RunningTeam> {

	private RunningDTOAdapter runningDTOAdapter;
	private TeamDTOAdapter teamDTOAdapter;
	private AcademicLevelDTOAdapter academicLevelDTOAdapter;

	public RunningTeamDTOAdapter() {
		runningDTOAdapter = new RunningDTOAdapter();
		teamDTOAdapter = new TeamDTOAdapter();
		academicLevelDTOAdapter = new AcademicLevelDTOAdapter();
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
		target.setAcademicLevel(academicLevelDTOAdapter.adapt(source.getAcademicLevel()));
	}
}
