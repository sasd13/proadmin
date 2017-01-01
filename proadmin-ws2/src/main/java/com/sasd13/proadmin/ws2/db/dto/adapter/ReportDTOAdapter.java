package com.sasd13.proadmin.ws2.db.dto.adapter;

import org.joda.time.DateTime;

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
		target.setDateMeeting(new DateTime(source.getD));
		
		
		
		target.setPlanningMark(source.getPlanningMark());
		target.setPlanningComment(source.getPlanningComment());
		target.setCommunicationMark(source.getCommunicationMark());
		target.setCommunicationComment(source.getCommunicationComment());
		target.setReport(source.getReport());
		target.setStudent(source.getStudent());
	}
}
