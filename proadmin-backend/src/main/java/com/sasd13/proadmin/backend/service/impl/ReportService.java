package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IReportDAO;
import com.sasd13.proadmin.backend.model.Report;
import com.sasd13.proadmin.backend.service.IReportService;
import com.sasd13.proadmin.backend.util.adapter.itf.ReportITFAdapter;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ReportService implements IReportService {

	@Autowired
	private IReportDAO reportDAO;

	private ReportITFAdapter adapter;

	public ReportService() {
		adapter = new ReportITFAdapter();
	}

	@Override
	public void create(ReportBean reportBean) {
		Report report = adapter.adaptI2M(reportBean);

		reportDAO.create(report);
	}

	@Override
	public void update(ReportBean reportBean) {
		Report report = adapter.adaptI2M(reportBean);

		reportDAO.update(report);
	}

	@Override
	public void delete(ReportBean reportBean) {
		Report report = adapter.adaptI2M(reportBean);

		reportDAO.delete(report);
	}

	@Override
	public List<ReportBean> read(Map<String, Object> criterias) {
		List<Report> reports = reportDAO.read(criterias);

		return adapter.adaptM2I(reports);
	}
}
