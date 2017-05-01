package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.update.ReportUpdate;

public interface IReportService {

	long create(Report report);

	void update(ReportUpdate reportUpdate);

	void delete(Report report);

	List<Report> read(Map<String, String[]> criterias);
}
