package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ReportService implements IReportService {

    private static final int NBR_REQUESTS = 3;

    @Override
    public ServiceResult<List<Report>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", WSResources.URL_WS_REPORTS, Report.class);

        promise.setParameters(parameters);

        List<Report> results = (List<Report>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters) {
        MultiReadPromise promise = new MultiReadPromise(NBR_REQUESTS);

        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(PARAMATERS_STUDENTTEAM, WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        requests[0].setParameters(allParameters.get(PARAMATERS_STUDENTTEAM));

        requests[1] = new MultiReadPromise.Request(PARAMETERS_LEADEVALUATION, WSResources.URL_WS_LEADEVALUATIONS, LeadEvaluation.class);
        requests[1].setParameters(allParameters.get(PARAMETERS_LEADEVALUATION));

        requests[2] = new MultiReadPromise.Request(PARAMETERS_INDIVIDUALEVALUATION, WSResources.URL_WS_INDIVIDUALEVALUATIONS, IndividualEvaluation.class);
        requests[2].setParameters(allParameters.get(PARAMETERS_INDIVIDUALEVALUATION));

        Map<String, Object> results = promise.execute(requests, 7000);

        return new ServiceResult<>(
                promise.isSuccess(),
                0,
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Report report) {
        Promise promise = new Promise("POST", WSResources.URL_WS_REPORTS);

        promise.execute(new Report[]{report});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(ReportUpdateWrapper reportUpdateWrapper) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_REPORTS);

        promise.execute(new ReportUpdateWrapper[]{reportUpdateWrapper});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Report> reports) {
        Promise promise = new Promise("DELETE", WSResources.URL_WS_REPORTS);

        promise.execute(reports);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }
}
