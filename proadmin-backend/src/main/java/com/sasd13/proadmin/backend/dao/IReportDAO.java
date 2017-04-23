package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Report;
import com.sasd13.proadmin.backend.dao.dto.ReportDTO;

public interface IReportDAO {

	ReportDTO create(Report report);

	void update(Report report);

	void delete(Report report);

	List<ReportDTO> read(Map<String, String[]> parameters);
}
