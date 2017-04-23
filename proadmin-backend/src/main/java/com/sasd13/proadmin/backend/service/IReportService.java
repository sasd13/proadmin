package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Report;

public interface IReportService {

	void create(Report report);

	void update(Report report);

	void delete(Report report);

	List<Report> read(Map<String, String[]> parameters);
}
