package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Report;
import com.sasd13.proadmin.backend.dao.dto.ReportDTO;
import com.sasd13.proadmin.backend.dao.dto.RunningTeamDTO;

public class ReportAdapterB2D implements IAdapter<Report, ReportDTO> {

	@Override
	public ReportDTO adapt(Report s) {
		ReportDTO t = new ReportDTO();

		t.setId(s.getId());
		t.setNumber(s.getNumber());
		t.setDateMeeting(s.getDateMeeting());
		t.setSession(s.getSession());
		t.setComment(s.getComment());

		RunningTeamDTO runningTeamDTO = new RunningTeamDTO();
		runningTeamDTO.setId(s.getRunningTeam().getId());
		t.setRunningTeam(runningTeamDTO);

		return t;
	}
}
