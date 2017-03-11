package com.sasd13.proadmin.ws2.db.dto.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.ws2.db.dto.ReportDTO;

public class ReportDTOAdapter implements IAdapter<ReportDTO, Report> {

	@Override
	public Report adapt(ReportDTO source) {
		Report target = new Report();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(ReportDTO source, Report target) {
		target.setNumber(source.getCode());
		target.setDateMeeting(source.getDateMeeting());
		target.setSession(source.getSession());
		target.setRunningTeam(new RunningTeamDTOAdapter().adapt(source.getRunningTeam()));
	}
}
