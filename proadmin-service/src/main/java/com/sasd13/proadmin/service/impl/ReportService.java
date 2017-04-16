package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.IReportDAO;
import com.sasd13.proadmin.dao.ReportDeepReader;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

public class ReportService implements IReportService {

	private IReportDAO session;
	private ReportDeepReader deepReader;

	public ReportService(DAO dao) {
		session = (IReportDAO) dao.getSession(IReportDAO.class);
		deepReader = (ReportDeepReader) dao.getDeepReader(ReportDeepReader.class);
	}

	@Override
	public long create(Report report) {
		return session.create(report);
	}

	@Override
	public void update(ReportUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(Report report) {
		session.delete(report);
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) {
		return deepReader.read(parameters);
	}

	@Override
	public List<Report> readAll() {
		return deepReader.readAll();
	}
}
