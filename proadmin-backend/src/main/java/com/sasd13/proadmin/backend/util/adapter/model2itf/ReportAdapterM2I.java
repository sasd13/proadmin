package com.sasd13.proadmin.backend.util.adapter.model2itf;

import java.text.SimpleDateFormat;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.model.Report;
import com.sasd13.proadmin.backend.util.Constants;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedRunningTeam;
import com.sasd13.proadmin.itf.bean.report.CoreInfo;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

public class ReportAdapterM2I implements IAdapter<Report, ReportBean> {

	@Override
	public ReportBean adapt(Report s) {
		ReportBean t = new ReportBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setNumber(s.getNumber());
		coreInfo.setDateMeeting(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).format(s.getDateMeeting()));
		coreInfo.setSession(s.getSession());
		coreInfo.setComment(s.getComment());
		t.setCoreInfo(coreInfo);

		LinkedRunningTeam linkedRunningTeam = new LinkedRunningTeam();
		linkedRunningTeam.setId(String.valueOf(s.getRunningTeam().getId()));
		t.setLinkedRunningTeam(linkedRunningTeam);

		return t;
	}
}
