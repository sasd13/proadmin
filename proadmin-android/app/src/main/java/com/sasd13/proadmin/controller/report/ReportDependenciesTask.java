package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.javaex.net.EnumHttpHeader;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.ServiceResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportDependenciesTask extends RequestorTask {

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

    @Override
    public Object execute(Object o) {
        ServiceResult<Map<String, Object>> out = service.retrieve(allParameters);

        return out;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<Map<String, Object>> result = (ServiceResult<Map<String, Object>>) out;

        if (result.isSuccess()) {
            controller.onRetrieved(result.getData());
        } else {
            controller.onFail(result.getHttpStatus(), result.getHeaders().get(EnumHttpHeader.RESPONSE_ERROR.getName()));
        }
    }
}
