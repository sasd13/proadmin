package com.sasd13.proadmin.service.ws;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class LeadEvaluationService {

    public interface Caller extends ReadService.Caller<LeadEvaluation>, ManageService.Caller {
    }

    private ReadService<LeadEvaluation> readService;
    private ManageService<LeadEvaluation> manageService;

    public LeadEvaluationService(Caller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_LEADEVALUATIONS, LeadEvaluation.class);
        manageService = new ManageService<>(caller, WSResources.URL_WS_LEADEVALUATIONS);
    }

    public void readByReport(String reportNumber) {
        readService.clearHeaders();
        readService.clearParameters();
        readService.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readService.putParameters(EnumParameter.REPORT.getName(), new String[]{reportNumber});
        readService.read();
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
