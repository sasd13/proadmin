package com.sasd13.proadmin.backend.util.adapter.itf2bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Report;
import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.backend.util.Constants;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

public class ReportAdapterI2B implements IAdapter<ReportBean, Report> {

	private static final Logger LOGGER = Logger.getLogger(ReportAdapterI2B.class);

	@Override
	public Report adapt(ReportBean s) {
		Report t = new Report();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		try {
			t.setDateMeeting(new SimpleDateFormat(Constants.PATTERN_DATE_FORMAT).parse(s.getCoreInfo().getDateMeeting()));
		} catch (ParseException e) {
			LOGGER.error(e);
			throw new RuntimeException("ReportAdapterI2B : error parsing date " + s.getCoreInfo().getDateMeeting());
		}

		t.setNumber(s.getCoreInfo().getNumber());
		t.setSession(s.getCoreInfo().getSession());
		t.setComment(s.getCoreInfo().getComment());

		RunningTeam runningTeam = new RunningTeam();
		runningTeam.setId(Long.valueOf(s.getLinkedRunningTeam().getId()));
		t.setRunningTeam(runningTeam);

		return t;
	}
}
