package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ManagePromise;
import com.sasd13.androidex.ws.rest.promise.ReadPromise;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationService {

    public interface Callback extends ReadPromise.Callback<LeadEvaluation>, ManagePromise.Callback {
    }

    private ReadPromise<LeadEvaluation> readPromise;
    private ManagePromise<LeadEvaluation> managePromise;

    public LeadEvaluationService(Callback callback) {
        readPromise = new ReadPromise<>(callback, WSResources.URL_WS_LEADEVALUATIONS, LeadEvaluation.class);
        managePromise = new ManagePromise<>(callback, WSResources.URL_WS_LEADEVALUATIONS);
    }

    public void readByReport(String reportNumber) {
        readPromise.clearHeaders();
        readPromise.clearParameters();
        readPromise.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readPromise.putParameters(EnumParameter.REPORT.getName(), new String[]{reportNumber});
        readPromise.read();
    }

    public void create(LeadEvaluation leadEvaluation, Report report) {
        leadEvaluation.setReport(report);

        managePromise.create(leadEvaluation);
    }

    public void update(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        leadEvaluation.setReport(leadEvaluationToUpdate.getReport());

        managePromise.update(getUpdateWrapper(leadEvaluation, leadEvaluationToUpdate));
    }

    private LeadEvaluationUpdateWrapper getUpdateWrapper(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        LeadEvaluationUpdateWrapper updateWrapper = new LeadEvaluationUpdateWrapper();

        updateWrapper.setWrapped(leadEvaluation);
        updateWrapper.setReportNumber(leadEvaluationToUpdate.getReport().getNumber());
        updateWrapper.setStudentNumber(leadEvaluationToUpdate.getStudent().getNumber());

        return updateWrapper;
    }
}
