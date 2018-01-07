package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IReportDAO;
import com.sasd13.proadmin.backend.entity.Report;
import com.sasd13.proadmin.backend.service.IReportService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.ReportAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.ReportAdapterM2I;
import com.sasd13.proadmin.itf.bean.report.ReportBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ReportService implements IReportService {

	@Autowired
	private IReportDAO reportDAO;

	@Override
	public void create(ReportBean reportBean) {
		Report report = adaptI2M(reportBean);

		reportDAO.create(report);
	}

	private Report adaptI2M(ReportBean reportBean) {
		return new ReportAdapterI2M().adapt(reportBean);
	}

	@Override
	public void update(ReportBean reportBean) {
		Report report = adaptI2M(reportBean);

		reportDAO.update(report);
	}

	@Override
	public void delete(ReportBean reportBean) {
		Report report = adaptI2M(reportBean);

		reportDAO.delete(report);
	}

	@Override
	public List<ReportBean> read(Map<String, Object> criterias) {
		List<Report> reports = reportDAO.read(criterias);

		return adaptM2I(reports);
	}

	private List<ReportBean> adaptM2I(List<Report> reports) {
		List<ReportBean> list = new ArrayList<>();
		ReportAdapterM2I adapter = new ReportAdapterM2I();

		for (Report report : reports) {
			list.add(adapter.adapt(report));
		}

		return list;
	}
}
