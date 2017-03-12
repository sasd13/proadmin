package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;

public interface IReportService {

	void create(Report report);

	void update(IReportUpdateWrapper updateWrapper);

	void delete(Report report);

	List<Report> read(Map<String, String[]> parameters);
}
