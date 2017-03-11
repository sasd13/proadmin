package com.sasd13.proadmin.ws2.db.dto.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.ws2.db.dto.RunningTeamDTO;

public class RunningTeamDTOAdapter implements IAdapter<RunningTeamDTO, RunningTeam> {

	@Override
	public RunningTeam adapt(RunningTeamDTO source) {
		RunningTeam target = new RunningTeam();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(RunningTeamDTO source, RunningTeam target) {
		target.setRunning(new RunningDTOAdapter().adapt(source.getRunning()));
		target.setTeam(new TeamDTOAdapter().adapt(source.getTeam()));
		target.setAcademicLevel(new AcademicLevelDTOAdapter().adapt(source.getAcademicLevel()));
	}
}
