package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.service.IReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportDependenciesStrategy extends RequestorStrategy {

    private ReportController controller;
    private IReportService service;
    private Map<String, Map<String, String[]>> allParameters;

    public ReportDependenciesStrategy(ReportController controller, IReportService service) {
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
    public Object doInBackgroung(Object[] in) {
        return service.retrieve(allParameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.onRetrieved((Map<String, List>) out);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
