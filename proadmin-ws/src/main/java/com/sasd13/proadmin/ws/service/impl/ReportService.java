package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.update.ReportUpdate;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IReportDAO;
import com.sasd13.proadmin.ws.dao.jdbc.ReportDeepReader;
import com.sasd13.proadmin.ws.service.IReportService;

public class ReportService implements IReportService {

	private IReportDAO reportDAO;
	private ReportDeepReader reportDeepReader;

	public ReportService(DAO dao) {
		reportDAO = (IReportDAO) dao.getSession(IReportDAO.class);
		reportDeepReader = (ReportDeepReader) dao.getDeepReader(ReportDeepReader.class);
	}

	@Override
	public long create(Report report) {
		return reportDAO.create(report);
	}

	@Override
	public void update(ReportUpdate reportUpdate) {
		reportDAO.update(reportUpdate);
	}

	@Override
	public void delete(Report report) {
		reportDAO.delete(report);
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) {
		return reportDeepReader.read(parameters);
	}
}
