package com.sasd13.proadmin.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

public interface IReportService {

	long create(Report report);

	void update(ReportUpdateWrapper updateWrapper);

	void delete(Report report);

	List<Report> read(Map<String, String[]> parameters);

	List<Report> readAll();
}
