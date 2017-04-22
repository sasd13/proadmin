package com.sasd13.proadmin.ws2.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.ws2.dao.dto.ReportDTO;

public class ReportDTOAdapter implements IAdapter<ReportDTO, Report> {

	private RunningTeamDTOAdapter runningTeamDTOAdapter;

	public ReportDTOAdapter() {
		runningTeamDTOAdapter = new RunningTeamDTOAdapter();
	}

	@Override
	public Report adapt(ReportDTO source) {
		Report target = new Report();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(ReportDTO source, Report target) {
		target.setNumber(source.getNumber());
		target.setDateMeeting(source.getDateMeeting());
		target.setSession(source.getSession());
		target.setComment(source.getComment());
		target.setRunningTeam(runningTeamDTOAdapter.adapt(source.getRunningTeam()));
	}
}
