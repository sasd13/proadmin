package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationService {

    private Promise promiseCreate, promiseUpdate;

    public LeadEvaluationService() {
        promiseCreate = new Promise("POST", WSResources.URL_WS_LEADEVALUATIONS);
        promiseUpdate = new Promise("PUT", WSResources.URL_WS_LEADEVALUATIONS);
    }

    public void create(ICallback callback, LeadEvaluation leadEvaluation, Report report) {
        leadEvaluation.setReport(report);

        promiseCreate.registerCallback(callback);
        promiseCreate.execute(leadEvaluation);
    }

    public void update(ICallback callback, LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        leadEvaluation.setReport(leadEvaluationToUpdate.getReport());

        promiseUpdate.registerCallback(callback);
        promiseUpdate.execute(getUpdateWrapper(leadEvaluation, leadEvaluationToUpdate));
    }

    private LeadEvaluationUpdateWrapper getUpdateWrapper(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        LeadEvaluationUpdateWrapper updateWrapper = new LeadEvaluationUpdateWrapper();

        updateWrapper.setWrapped(leadEvaluation);
        updateWrapper.setReportNumber(leadEvaluationToUpdate.getReport().getNumber());

        return updateWrapper;
    }
}
