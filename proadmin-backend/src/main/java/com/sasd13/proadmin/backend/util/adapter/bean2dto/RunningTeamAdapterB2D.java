package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.backend.dao.dto.AcademicLevelDTO;
import com.sasd13.proadmin.backend.dao.dto.RunningDTO;
import com.sasd13.proadmin.backend.dao.dto.RunningTeamDTO;
import com.sasd13.proadmin.backend.dao.dto.TeamDTO;

public class RunningTeamAdapterB2D implements IAdapter<RunningTeam, RunningTeamDTO> {

	@Override
	public RunningTeamDTO adapt(RunningTeam s) {
		RunningTeamDTO t = new RunningTeamDTO();

		t.setId(s.getId());

		RunningDTO runningDTO = new RunningDTO();
		runningDTO.setId(s.getRunning().getId());
		t.setRunning(runningDTO);

		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setId(s.getTeam().getId());
		t.setTeam(teamDTO);

		AcademicLevelDTO academicLevelDTO = new AcademicLevelDTO();
		academicLevelDTO.setId(s.getAcademicLevel().getId());
		t.setAcademicLevel(academicLevelDTO);

		return t;
	}
}
