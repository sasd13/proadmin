package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.dao.dto.ReportDTO;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

public interface IReportDAO {

	ReportDTO create(Report report);

	void update(List<ReportUpdateWrapper> updateWrappers);

	void delete(List<Report> reports);

	List<ReportDTO> read(Map<String, String[]> parameters);
}
