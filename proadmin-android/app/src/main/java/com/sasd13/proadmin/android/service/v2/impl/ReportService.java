package com.sasd13.proadmin.android.service.v2.impl;

import com.sasd13.androidex.net.promise.MultiReadPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.service.v2.IReportService;
import com.sasd13.proadmin.android.util.adapter.bean2itf.v2.ReportAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v2.IndividualEvaluationAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v2.LeadEvaluationAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v2.ReportAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v2.StudentTeamAdapterI2B;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationResponseBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationResponseBean;
import com.sasd13.proadmin.itf.bean.report.ReportBean;
import com.sasd13.proadmin.itf.bean.report.ReportRequestBean;
import com.sasd13.proadmin.itf.bean.report.ReportResponseBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
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
        Promise promise = new Promise("GET", Resources.URL_BACKEND_REPORTS + "/search", ReportResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(parameters);

        ReportResponseBean responseBean = (ReportResponseBean) promise.execute();
        List<Report> list = new ArrayList<>();

        if (promise.isSuccess()) {
            ReportAdapterI2B adapter = new ReportAdapterI2B();

            for (ReportBean reportBean : responseBean.getData()) {
                list.add(adapter.adapt(reportBean));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                promise.isSuccess() ? list : Collections.<Report>emptyList()
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, String[]>> allParameters) {
        MultiReadPromise promise = new MultiReadPromise(NBR_REQUESTS);

        MultiReadPromise.Request[] requests = new MultiReadPromise.Request[NBR_REQUESTS];

        requests[0] = new MultiReadPromise.Request(PARAMATERS_STUDENTTEAM, Resources.URL_BACKEND_STUDENTTEAMS, StudentTeamResponseBean.class);
        requests[0].setParameters(allParameters.get(PARAMATERS_STUDENTTEAM));

        requests[1] = new MultiReadPromise.Request(PARAMETERS_LEADEVALUATION, Resources.URL_BACKEND_LEADEVALUATIONS, LeadEvaluationResponseBean.class);
        requests[1].setParameters(allParameters.get(PARAMETERS_LEADEVALUATION));

        requests[2] = new MultiReadPromise.Request(PARAMETERS_INDIVIDUALEVALUATION, Resources.URL_BACKEND_INDIVIDUALEVALUATIONS, IndividualEvaluationResponseBean.class);
        requests[2].setParameters(allParameters.get(PARAMETERS_INDIVIDUALEVALUATION));

        Map<String, Object> results = promise.execute(requests, 7000);

        StudentTeamResponseBean studentTeamResponseBean = (StudentTeamResponseBean) results.get(PARAMATERS_STUDENTTEAM);
        LeadEvaluationResponseBean leadEvaluationResponseBean = (LeadEvaluationResponseBean) results.get(PARAMETERS_LEADEVALUATION);
        IndividualEvaluationResponseBean individualEvaluationResponseBean = (IndividualEvaluationResponseBean) results.get(PARAMETERS_INDIVIDUALEVALUATION);

        List<StudentTeam> studentTeams = new ArrayList<>();
        List<LeadEvaluation> leadEvaluations = new ArrayList<>();
        List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

        if (promise.isSuccess()) {
            StudentTeamAdapterI2B studentTeamAdapter = new StudentTeamAdapterI2B();
            LeadEvaluationAdapterI2B leadEvaluationAdapter = new LeadEvaluationAdapterI2B();
            IndividualEvaluationAdapterI2B individualEvaluationAdapter = new IndividualEvaluationAdapterI2B();

            for (StudentTeamBean studentTeamBean : studentTeamResponseBean.getData()) {
                studentTeams.add(studentTeamAdapter.adapt(studentTeamBean));
            }

            for (LeadEvaluationBean leadEvaluationBean : leadEvaluationResponseBean.getData()) {
                leadEvaluations.add(leadEvaluationAdapter.adapt(leadEvaluationBean));
            }

            for (IndividualEvaluationBean individualEvaluationBean : individualEvaluationResponseBean.getData()) {
                individualEvaluations.add(individualEvaluationAdapter.adapt(individualEvaluationBean));
            }
        }

        results.put(PARAMATERS_STUDENTTEAM, studentTeams);
        results.put(PARAMETERS_LEADEVALUATION, leadEvaluations);
        results.put(PARAMETERS_INDIVIDUALEVALUATION, individualEvaluations);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.isSuccess() ? 200 : 417,
                Collections.<String, String>emptyMap(),
                promise.isSuccess() ? results : Collections.<String, Object>emptyMap()
        );
    }

    @Override
    public ServiceResult<Void> create(Report report) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_REPORTS);

        ReportRequestBean requestBean = new ReportRequestBean();
        List<ReportBean> list = new ArrayList<>();

        list.add(new ReportAdapterB2I().adapt(report));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(Report report) {
        Promise promise = new Promise("PUT", Resources.URL_BACKEND_REPORTS);

        ReportRequestBean requestBean = new ReportRequestBean();
        List<ReportBean> list = new ArrayList<>();

        list.add(new ReportAdapterB2I().adapt(report));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Report> reports) {
        Promise promise = new Promise("DELETE", Resources.URL_BACKEND_REPORTS);

        ReportRequestBean requestBean = new ReportRequestBean();
        List<ReportBean> list = new ArrayList<>();
        ReportAdapterB2I adapter = new ReportAdapterB2I();

        for (Report report : reports) {
            list.add(adapter.adapt(report));
        }

        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
