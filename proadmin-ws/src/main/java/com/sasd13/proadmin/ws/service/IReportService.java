package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

public interface IReportService {

	long create(IReport iReport);

	void update(ReportUpdate updateWrapper);

	void delete(IReport iReport);

	List<IReport> read(Map<String, String[]> parameters);

	List<IReport> readAll();
}
