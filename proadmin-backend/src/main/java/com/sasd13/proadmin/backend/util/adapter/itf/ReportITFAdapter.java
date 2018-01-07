package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.Report;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.ReportAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.ReportAdapterM2I;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

public class ReportITFAdapter extends ITFAdapter<Report, ReportBean> {

	public ReportITFAdapter() {
		super(new ReportAdapterI2M(), new ReportAdapterM2I());
	}
}
