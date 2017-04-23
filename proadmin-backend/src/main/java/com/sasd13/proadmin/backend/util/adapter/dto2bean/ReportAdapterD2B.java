package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Report;
import com.sasd13.proadmin.backend.dao.dto.ReportDTO;

public class ReportAdapterD2B implements IAdapter<ReportDTO, Report> {

	private RunningTeamAdapterD2B runningTeamAdapter;

	public ReportAdapterD2B() {
		runningTeamAdapter = new RunningTeamAdapterD2B();
	}

	@Override
	public Report adapt(ReportDTO s) {
		Report t = new Report();

		t.setId(s.getId());
		t.setNumber(s.getNumber());
		t.setDateMeeting(s.getDateMeeting());
		t.setSession(s.getSession());
		t.setComment(s.getComment());
		t.setRunningTeam(runningTeamAdapter.adapt(s.getRunningTeam()));

		return t;
	}
}
