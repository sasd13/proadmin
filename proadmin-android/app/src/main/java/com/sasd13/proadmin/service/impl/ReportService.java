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

    private Promise promiseRead, promiseCreate, promiseUpdate, promiseDelete;
    private MultiReadPromise multiPromiseRead;

    @Override
    public ServiceResult<List<Report>> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_REPORTS, Report.class);
        }

        promiseRead.setParameters(parameters);

        List<Report> results = (List<Report>) promiseRead.execute();

        return new ServiceResult<>(
                promiseRead.isSuccess(),
                promiseRead.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters) {
        if (multiPromiseRead == null) {
            multiPromiseRead = new MultiReadPromise(NBR_REQUESTS);
        }

        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(PARAMATERS_STUDENTTEAM, WSResources.URL_WS_STUDENTTEAMS, StudentTeam.class);
        requests[0].setParameters(allParameters.get(PARAMATERS_STUDENTTEAM));

        requests[1] = new MultiReadPromise.Request(PARAMETERS_LEADEVALUATION, WSResources.URL_WS_LEADEVALUATIONS, LeadEvaluation.class);
        requests[1].setParameters(allParameters.get(PARAMETERS_LEADEVALUATION));

        requests[2] = new MultiReadPromise.Request(PARAMETERS_INDIVIDUALEVALUATION, WSResources.URL_WS_INDIVIDUALEVALUATIONS, IndividualEvaluation.class);
        requests[2].setParameters(allParameters.get(PARAMETERS_INDIVIDUALEVALUATION));

        Map<String, Object> results = multiPromiseRead.execute(requests);

        return new ServiceResult<>(
                multiPromiseRead.isSuccess(),
                0,
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Report report) {
        if (promiseCreate == null) {
            promiseCreate = new Promise("POST", WSResources.URL_WS_REPORTS);
        }

        promiseCreate.execute(report);

        return new ServiceResult<>(
                promiseCreate.isSuccess(),
                promiseCreate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(ReportUpdateWrapper reportUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_REPORTS);
        }

        promiseUpdate.execute(reportUpdateWrapper);

        return new ServiceResult<>(
                promiseUpdate.isSuccess(),
                promiseUpdate.getResponseCode(),
                null
        );
    }

    @Override
    public ServiceResult<Void> delete(Report[] reports) {
        if (promiseDelete == null) {
            promiseDelete = new Promise("DELETE", WSResources.URL_WS_REPORTS);
        }

        promiseDelete.execute(reports);

        return new ServiceResult<>(
                promiseDelete.isSuccess(),
                promiseDelete.getResponseCode(),
                null
        );
    }
}
