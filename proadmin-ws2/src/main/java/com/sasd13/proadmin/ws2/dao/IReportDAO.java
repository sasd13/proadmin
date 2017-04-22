package com.sasd13.proadmin.ws2.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.ws2.dao.dto.ReportDTO;

public interface IReportDAO {

	ReportDTO create(Report report);

	void update(List<ReportUpdateWrapper> updateWrappers);

	void delete(List<Report> reports);

	List<ReportDTO> read(Map<String, String[]> parameters);
}
