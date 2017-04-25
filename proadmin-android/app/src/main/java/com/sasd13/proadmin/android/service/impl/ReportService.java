package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.update.ReportUpdate;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ReportService implements IReportService {

    private static final int NBR_REQUESTS = 3;

    @Override
    public ServiceResult<List<Report>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_WS_REPORTS, Report.class);

        promise.setParameters(parameters);

        List<Report> results = (List<Report>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                results
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters) {
        MultiReadPromise promise = new MultiReadPromise(NBR_REQUESTS);

        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(PARAMATERS_STUDENTTEAM, Resources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        requests[0].setParameters(allParameters.get(PARAMATERS_STUDENTTEAM));

        requests[1] = new MultiReadPromise.Request(PARAMETERS_LEADEVALUATION, Resources.URL_WS_LEADEVALUATIONS, LeadEvaluation.class);
        requests[1].setParameters(allParameters.get(PARAMETERS_LEADEVALUATION));

        requests[2] = new MultiReadPromise.Request(PARAMETERS_INDIVIDUALEVALUATION, Resources.URL_WS_INDIVIDUALEVALUATIONS, IndividualEvaluation.class);
        requests[2].setParameters(allParameters.get(PARAMETERS_INDIVIDUALEVALUATION));

        Map<String, Object> results = promise.execute(requests, 7000);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.isSuccess() ? 200 : 417,
                Collections.<String, String>emptyMap(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Report report) {
        Promise promise = new Promise("POST", Resources.URL_WS_REPORTS);

        promise.execute(new Report[]{report});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(ReportUpdate reportUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_WS_REPORTS);

        promise.execute(new ReportUpdate[]{reportUpdate});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Report> reports) {
        Promise promise = new Promise("DELETE", Resources.URL_WS_REPORTS);

        promise.execute(reports);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }
}
