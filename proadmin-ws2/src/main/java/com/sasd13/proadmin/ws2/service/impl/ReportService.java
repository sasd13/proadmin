package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IReportDAO;
import com.sasd13.proadmin.ws2.db.dto.ReportDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.ReportDTOAdapter;
import com.sasd13.proadmin.ws2.service.IReportService;

@Service
public class ReportService implements IReportService {

	@Autowired
	private IReportDAO reportDAO;

	@Override
	public void create(Report report) {
		reportDAO.create(report);
	}

	@Override
	public void update(IReportUpdateWrapper updateWrapper) {
		reportDAO.update(updateWrapper);
	}

	@Override
	public void delete(Report report) {
		reportDAO.delete(report);
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
