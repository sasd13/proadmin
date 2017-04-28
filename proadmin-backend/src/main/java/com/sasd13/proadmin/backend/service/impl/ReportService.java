package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.dao.IReportDAO;
import com.sasd13.proadmin.backend.model.Report;
import com.sasd13.proadmin.backend.service.IReportService;

@Service
public class ReportService implements IReportService {

	@Autowired
	private IReportDAO reportDAO;

	@Override
	public void create(Report report) {
		reportDAO.create(report);
	}

	@Override
	public void update(Report report) {
		reportDAO.update(report);
	}

	@Override
	public void delete(Report report) {
		reportDAO.delete(report);
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) {
		return reportDAO.read(parameters);
	}
}
