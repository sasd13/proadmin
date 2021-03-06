package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.MultiPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.model.IndividualEvaluation;
import com.sasd13.proadmin.android.model.LeadEvaluation;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.itf.IndividualEvaluationITFAdapter;
import com.sasd13.proadmin.android.util.adapter.itf.LeadEvaluationITFAdapter;
import com.sasd13.proadmin.android.util.adapter.itf.ReportITFAdapter;
import com.sasd13.proadmin.android.util.adapter.itf.StudentTeamITFAdapter;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationResponseBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationResponseBean;
import com.sasd13.proadmin.itf.bean.report.ReportRequestBean;
import com.sasd13.proadmin.itf.bean.report.ReportResponseBean;
import com.sasd13.proadmin.itf.bean.studentteam.StudentTeamResponseBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ReportService implements IReportService {

    private static final String URL_WS2_REPORTS = AppProperties.getProperty(Names.URL_WS2_REPORTS);
    private static final String URL_WS2_STUDENTTEAMS = AppProperties.getProperty(Names.URL_WS2_STUDENTTEAMS);
    private static final String URL_WS2_LEADEVALUATIONS = AppProperties.getProperty(Names.URL_WS2_LEADEVALUATIONS);
    private static final String URL_WS2_INDIVIDUALEVALUATIONS = AppProperties.getProperty(Names.URL_WS2_INDIVIDUALEVALUATIONS);
    private static final int NBR_REQUESTS = 3;

    private ReportITFAdapter reportAdapter;
    private StudentTeamITFAdapter studentTeamAdapter;
    private LeadEvaluationITFAdapter leadEvaluationAdapter;
    private IndividualEvaluationITFAdapter individualEvaluationAdapter;

    public ReportService() {
        reportAdapter = new ReportITFAdapter();
        studentTeamAdapter = new StudentTeamITFAdapter();
        leadEvaluationAdapter = new LeadEvaluationITFAdapter();
        individualEvaluationAdapter = new IndividualEvaluationITFAdapter();
    }

    @Override
    public ServiceResult<List<Report>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", URL_WS2_REPORTS + "/search", ReportResponseBean.class);
        SearchBean searchBean = new SearchBean();

        searchBean.setCriterias(criterias);

        ReportResponseBean responseBean = (ReportResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        List<Report> list = new ArrayList<>();

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                list = reportAdapter.adaptI2M(responseBean.getData());
            }
        }

        return new ServiceResult<>(
                promise.isSuccess() && errors.isEmpty(),
                promise.getResponseCode(),
                errors,
                list
        );
    }

    @Override
    public ServiceResult<Map<String, Object>> retrieve(Map<String, Map<String, Object>> allCriterias) {
        MultiPromise.Request[] requests = new MultiPromise.Request[NBR_REQUESTS];
        requests[0] = new MultiPromise.Request(PARAMATERS_STUDENTTEAM, "POST", URL_WS2_STUDENTTEAMS + "/search", StudentTeamResponseBean.class);
        requests[1] = new MultiPromise.Request(PARAMETERS_LEADEVALUATION, "POST", URL_WS2_LEADEVALUATIONS + "/search", LeadEvaluationResponseBean.class);
        requests[2] = new MultiPromise.Request(PARAMETERS_INDIVIDUALEVALUATION, "POST", URL_WS2_INDIVIDUALEVALUATIONS + "/search", IndividualEvaluationResponseBean.class);

        SearchBean searchBeanStudentTeams = new SearchBean();
        searchBeanStudentTeams.setCriterias(allCriterias.get(PARAMATERS_STUDENTTEAM));
        requests[0].setBody(searchBeanStudentTeams);

        SearchBean searchBeanLeadEvaluations = new SearchBean();
        searchBeanLeadEvaluations.setCriterias(allCriterias.get(PARAMETERS_LEADEVALUATION));
        requests[1].setBody(searchBeanLeadEvaluations);

        SearchBean searchBeanIndividualEvaluations = new SearchBean();
        searchBeanIndividualEvaluations.setCriterias(allCriterias.get(PARAMETERS_INDIVIDUALEVALUATION));
        requests[2].setBody(searchBeanIndividualEvaluations);

        MultiPromise promise = new MultiPromise(NBR_REQUESTS);
        Map<String, Object> results = promise.execute(requests, 7000);

        StudentTeamResponseBean studentTeamResponseBean = (StudentTeamResponseBean) results.get(PARAMATERS_STUDENTTEAM);
        LeadEvaluationResponseBean leadEvaluationResponseBean = (LeadEvaluationResponseBean) results.get(PARAMETERS_LEADEVALUATION);
        IndividualEvaluationResponseBean individualEvaluationResponseBean = (IndividualEvaluationResponseBean) results.get(PARAMETERS_INDIVIDUALEVALUATION);

        List<StudentTeam> studentTeams = new ArrayList<>();
        List<LeadEvaluation> leadEvaluations = new ArrayList<>();
        List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

        if (promise.isSuccess()) {
            studentTeams = studentTeamAdapter.adaptI2M(studentTeamResponseBean.getData());
            leadEvaluations = leadEvaluationAdapter.adaptI2M(leadEvaluationResponseBean.getData());
            individualEvaluations = individualEvaluationAdapter.adaptI2M(individualEvaluationResponseBean.getData());
        }

        results.put(PARAMATERS_STUDENTTEAM, studentTeams);
        results.put(PARAMETERS_LEADEVALUATION, leadEvaluations);
        results.put(PARAMETERS_INDIVIDUALEVALUATION, individualEvaluations);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.isSuccess() ? 200 : 417,
                Collections.<String, String>emptyMap(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Report report) {
        Promise promise = new Promise("POST", URL_WS2_REPORTS + "/create");
        ReportRequestBean requestBean = new ReportRequestBean();

        requestBean.setData(reportAdapter.adaptM2I(Arrays.asList(report)));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(Report report) {
        Promise promise = new Promise("POST", URL_WS2_REPORTS + "/update");
        ReportRequestBean requestBean = new ReportRequestBean();

        requestBean.setData(Arrays.asList(reportAdapter.adaptM2I(report)));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> delete(List<Report> reports) {
        Promise promise = new Promise("POST", URL_WS2_REPORTS + "/delete");
        ReportRequestBean requestBean = new ReportRequestBean();

        requestBean.setData(reportAdapter.adaptM2I(reports));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
