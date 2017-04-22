package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

public interface IReportService {

	void create(Report report);

	void update(List<ReportUpdateWrapper> updateWrappers);

	void delete(List<Report> reports);

	List<Report> read(Map<String, String[]> parameters);
}
