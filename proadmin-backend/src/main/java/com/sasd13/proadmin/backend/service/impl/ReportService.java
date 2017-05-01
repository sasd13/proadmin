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

@Service
@Transactional(propagation = Propagation.REQUIRED)
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
	public List<Report> read(Map<String, Object> criterias) {
		return reportDAO.read(criterias);
	}
}
