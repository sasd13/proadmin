package com.sasd13.proadmin.ws.util.adapter.bean2itf;

import java.text.SimpleDateFormat;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.report.CoreInfo;
import com.sasd13.proadmin.itf.bean.report.ReportBean;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.util.Constants;

public class ReportAdapterB2I implements IAdapter<Report, ReportBean> {

	@Override
	public ReportBean adapt(Report s) {
		ReportBean t = new ReportBean();

		Id id = new Id();
		id.setId(s.getNumber());
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setNumber(s.getNumber());
		coreInfo.setDateMeeting(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).format(s.getDateMeeting()));
		coreInfo.setSession(s.getSession());
		coreInfo.setComment(s.getComment());
		t.setCoreInfo(coreInfo);

		return t;
	}
}
