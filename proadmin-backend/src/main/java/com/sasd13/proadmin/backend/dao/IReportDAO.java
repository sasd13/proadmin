package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.entity.Report;

public interface IReportDAO {

	Report create(Report report);

	void update(Report report);

	void delete(Report report);

	List<Report> read(Map<String, Object> criterias);
}
