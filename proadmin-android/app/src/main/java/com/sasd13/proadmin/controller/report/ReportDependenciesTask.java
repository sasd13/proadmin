package com.sasd13.proadmin.controller.report;

import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportDependenciesTask {

    private ReportController controller;
    private IReportService service;
    private Map<String, Map<String, String[]>> allParameters;

    public ReportDependenciesTask(ReportController controller, IReportService service) {
        super();

        this.controller = controller;
        this.service = service;
        allParameters = new HashMap<>();

        resetParameters();
    }

    public void resetParameters() {
        allParameters.clear();
        allParameters.put(IReportService.PARAMATERS_STUDENTTEAM, new HashMap<String, String[]>());
        allParameters.put(IReportService.PARAMETERS_LEADEVALUATION, new HashMap<String, String[]>());
        allParameters.put(IReportService.PARAMETERS_INDIVIDUALEVALUATION, new HashMap<String, String[]>());
    }

    public void putParameter(String code, String key, String[] values) {
        allParameters.get(code).put(key, values);
    }

    public void execute() {
        ServiceResult<Map<String, Object>> out = doInBackgroung();

        onPostExecute(out);
    }

    private ServiceResult<Map<String, Object>> doInBackgroung() {
        return service.retrieve(allParameters);
    }

    private void onPostExecute(ServiceResult<Map<String, Object>> out) {
        if (out.isSuccess()) {
            controller.onRetrieved(out.getResult());
        } else {
            controller.display(EnumErrorRes.find(out.getHttpStatus()).getStringRes());
        }
    }
}
