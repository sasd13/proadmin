package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.ReportDTO;

public interface IReportDAO {

	ReportDTO create(Report report);

	void update(IReportUpdateWrapper updateWrapper);

	void delete(Report report);

	List<ReportDTO> read(Map<String, String[]> parameters);
}
