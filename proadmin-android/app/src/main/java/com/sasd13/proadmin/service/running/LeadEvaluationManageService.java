package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.LeadEvaluationForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.wrapper.update.running.ILeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class LeadEvaluationManageService implements IHttpCallback {

    private IManageServiceCaller<LeadEvaluation> serviceCaller;
    private UpdateTask<LeadEvaluation> updateTask;

    public LeadEvaluationManageService(IManageServiceCaller<LeadEvaluation> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void update(LeadEvaluationForm leadEvaluationForm, LeadEvaluation leadEvaluation) {
        updateTask = new UpdateTask<>(WSResources.URL_WS_LEADEVALUATIONS, this);

        try {
            updateTask.execute(getLeadEvaluationUpdateWrapper(leadEvaluationForm, leadEvaluation));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private ILeadEvaluationUpdateWrapper getLeadEvaluationUpdateWrapper(LeadEvaluationForm leadEvaluationForm, LeadEvaluation leadEvaluation) throws FormException {
        ILeadEvaluationUpdateWrapper leadEvaluationUpdateWrapper = new LeadEvaluationUpdateWrapper();

        leadEvaluationUpdateWrapper.setWrapped(getLeadEvaluationToUpdate(leadEvaluationForm, leadEvaluation.getReport()));
        leadEvaluationUpdateWrapper.setReportNumber(leadEvaluation.getReport().getNumber());
        leadEvaluationUpdateWrapper.setStudentNumber(leadEvaluation.getStudent().getNumber());

        return leadEvaluationUpdateWrapper;
    }

    private LeadEvaluation getLeadEvaluationToUpdate(LeadEvaluationForm leadEvaluationForm, Report report) throws FormException {
        LeadEvaluation leadEvaluationToUpdate = new LeadEvaluation(report.getNumber(), leadEvaluationForm.getLeader().getNumber());

        leadEvaluationToUpdate.setReport(report);
        leadEvaluationToUpdate.setPlanningMark(leadEvaluationForm.getPlanningMark());
        leadEvaluationToUpdate.setPlanningComment(leadEvaluationForm.getPlanningComment());
        leadEvaluationToUpdate.setCommunicationMark(leadEvaluationForm.getCommunicationMark());
        leadEvaluationToUpdate.setCommunicationComment(leadEvaluationForm.getCommunicationComment());

        return leadEvaluationToUpdate;
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, updateTask.getResponseErrors());
        } else {
            serviceCaller.onUpdateSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}