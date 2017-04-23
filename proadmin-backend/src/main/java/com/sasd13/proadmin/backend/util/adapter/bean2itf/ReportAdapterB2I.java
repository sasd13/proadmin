package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import java.text.SimpleDateFormat;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Report;
import com.sasd13.proadmin.backend.util.Constants;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedInfo;
import com.sasd13.proadmin.itf.bean.report.CoreInfo;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

public class ReportAdapterB2I implements IAdapter<Report, ReportBean> {

	@Override
	public ReportBean adapt(Report s) {
		ReportBean t = new ReportBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setNumber(s.getNumber());
		coreInfo.setDateMeeting(new SimpleDateFormat(Constants.PATTERN_DATE).format(s.getDateMeeting()));
		coreInfo.setSession(String.valueOf(s.getSession()));
		coreInfo.setComment(s.getComment());
		t.setCoreInfo(coreInfo);

		LinkedInfo linkedRunningTeam = new LinkedInfo();
		linkedRunningTeam.setId(String.valueOf(s.getRunningTeam().getId()));
		t.setLinkedRunningTeam(linkedRunningTeam);

		return t;
	}
}
