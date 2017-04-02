package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IReportService {

    List<Report> read(Map<String, String[]> parameters);

    Map<String, Object> retrieve(Map<String, Map<String, String[]>> allParameters);

    void create(Report report);

    void update(ReportUpdateWrapper reportUpdateWrapper);

    void delete(Report[] reports);
}
