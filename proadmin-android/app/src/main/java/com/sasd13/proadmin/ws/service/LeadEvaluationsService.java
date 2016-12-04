package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationsService {

    private ManageService<LeadEvaluation> manageService;

    public LeadEvaluationsService(ManageService.Caller caller) {
        manageService = new ManageService<>(caller, WSResources.URL_WS_LEADEVALUATIONS);
    }

    public void update(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        manageService.update(getUpdateWrapper(leadEvaluation, leadEvaluationToUpdate));
    }

    private LeadEvaluationUpdateWrapper getUpdateWrapper(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        LeadEvaluationUpdateWrapper updateWrapper = new LeadEvaluationUpdateWrapper();

        updateWrapper.setWrapped(leadEvaluation);
        updateWrapper.setReportNumber(leadEvaluationToUpdate.getReport().getNumber());
        updateWrapper.setStudentNumber(leadEvaluationToUpdate.getStudent().getNumber());

        return updateWrapper;
    }
}
