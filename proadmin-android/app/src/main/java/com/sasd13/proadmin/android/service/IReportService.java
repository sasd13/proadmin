package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 01/04/2017.
 */

public interface IReportService {

    String PARAMATERS_STUDENTTEAM = "PARAMATERS_STUDENTTEAM";
    String PARAMETERS_LEADEVALUATION = "PARAMETERS_LEADEVALUATION";
    String PARAMETERS_INDIVIDUALEVALUATION = "PARAMETERS_INDIVIDUALEVALUATION";

    ServiceResult<List<Report>> read(Map<String, Object> criterias);

    ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, Object>> allCriterias);

    ServiceResult<Void> create(Report report);

    ServiceResult<Void> update(Report report);

    ServiceResult<Void> delete(List<Report> reports);
}
