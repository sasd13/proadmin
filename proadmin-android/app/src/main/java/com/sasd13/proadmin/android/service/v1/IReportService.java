package com.sasd13.proadmin.android.service.v1;

import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.update.ReportUpdate;
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

    ServiceResult<List<Report>> read(Map<String, String[]> parameters);

    ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters);

    ServiceResult<Void> create(Report report);

    ServiceResult<Void> update(ReportUpdate reportUpdate);

    ServiceResult<Void> delete(List<Report> reports);
}
