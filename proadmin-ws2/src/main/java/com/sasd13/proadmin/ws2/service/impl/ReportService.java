package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IReportDAO;
import com.sasd13.proadmin.ws2.db.dto.ReportDTO;
import com.sasd13.proadmin.ws2.service.IReportService;
import com.sasd13.proadmin.ws2.util.adapter.ReportDTOAdapter;

@Service
public class ReportService implements IReportService {

	@Autowired
	private IReportDAO reportDAO;

	@Override
	public void create(Report report) {
		reportDAO.create(report);
	}

	@Override
	public void update(List<ReportUpdateWrapper> updateWrappers) {
		reportDAO.update(updateWrappers);
	}

	@Override
	public void delete(List<Report> reports) {
		reportDAO.delete(reports);
	}

	@Override
	public List<Report> read(Map<String, String[]> parameters) {
		List<Report> reports = new ArrayList<>();

		List<ReportDTO> dtos = reportDAO.read(parameters);
		ReportDTOAdapter adapter = new ReportDTOAdapter();

		for (ReportDTO dto : dtos) {
			reports.add(adapter.adapt(dto));
		}

		return reports;
	}
}
