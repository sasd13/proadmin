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
		target.setPlanningMark(source.getPlanningMark());
		target.setPlanningComment(source.getPlanningComment());
		target.setCommunicationMark(source.getCommunicationMark());
		target.setCommunicationComment(source.getCommunicationComment());
		target.setReport(source.getReport());
		target.setStudent(source.getStudent());
	}
}
