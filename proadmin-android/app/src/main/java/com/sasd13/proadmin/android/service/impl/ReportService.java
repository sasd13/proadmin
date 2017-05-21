package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.MultiPromise;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.ReportAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.IndividualEvaluationAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.LeadEvaluationAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.ReportAdapterI2B;
import com.sasd13.proadmin.android.util.adapter.itf2bean.StudentTeamAdapterI2B;
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
    public ServiceResult<List<Report>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_REPORTS + "/search", ReportResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(criterias);

        ReportResponseBean responseBean = (ReportResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        List<Report> list = new ArrayList<>();

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                ReportAdapterI2B adapter = new ReportAdapterI2B();

                for (ReportBean reportBean : responseBean.getData()) {
                    list.add(adapter.adapt(reportBean));
                }
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
        MultiPromise promise = new MultiPromise(NBR_REQUESTS);

        MultiPromise.Request[] requests = new MultiPromise.Request[NBR_REQUESTS];
        requests[0] = new MultiPromise.Request(PARAMATERS_STUDENTTEAM, "POST", Resources.URL_BACKEND_STUDENTTEAMS + "/search", StudentTeamResponseBean.class);
        requests[1] = new MultiPromise.Request(PARAMETERS_LEADEVALUATION, "POST", Resources.URL_BACKEND_LEADEVALUATIONS + "/search", LeadEvaluationResponseBean.class);
        requests[2] = new MultiPromise.Request(PARAMETERS_INDIVIDUALEVALUATION, "POST", Resources.URL_BACKEND_INDIVIDUALEVALUATIONS + "/search", IndividualEvaluationResponseBean.class);

        SearchBean searchBeanStudentTeams = new SearchBean();
        searchBeanStudentTeams.setCriterias(allCriterias.get(PARAMATERS_STUDENTTEAM));
        requests[0].setBody(searchBeanStudentTeams);

        SearchBean searchBeanLeadEvaluations = new SearchBean();
        searchBeanLeadEvaluations.setCriterias(allCriterias.get(PARAMETERS_LEADEVALUATION));
        requests[1].setBody(searchBeanLeadEvaluations);

        SearchBean searchBeanIndividualEvaluations = new SearchBean();
        searchBeanIndividualEvaluations.setCriterias(allCriterias.get(PARAMETERS_INDIVIDUALEVALUATION));
        requests[2].setBody(searchBeanIndividualEvaluations);

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
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Report report) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_REPORTS + "/create");

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
        Promise promise = new Promise("POST", Resources.URL_BACKEND_REPORTS + "/update");

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
        Promise promise = new Promise("POST", Resources.URL_BACKEND_REPORTS + "/delete");

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
