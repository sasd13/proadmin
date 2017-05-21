package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.report.ReportBean;

public interface IReportService {

	void create(ReportBean reportBean);

	void update(ReportBean reportBean);

	void delete(ReportBean reportBean);

	List<ReportBean> read(Map<String, Object> criterias);
}
