package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsForm;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsFormException;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.running.IndividualEvaluationBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IIndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndividualEvaluationsManageService implements IHttpCallback {

    private IManageServiceCaller<IndividualEvaluation> serviceCaller;
    private UpdateTask<IndividualEvaluation> updateTask;

    public IndividualEvaluationsManageService(IManageServiceCaller<IndividualEvaluation> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void update(IndividualEvaluationsForm individualEvaluationsForm, List<IndividualEvaluation> individualEvaluations) {
        updateTask = new UpdateTask<>(WSResources.URL_WS_INDIVIDUALEVALUATIONS, this);

        try {
            updateTask.execute(getIndividualEvaluationUpdateWrappers(individualEvaluationsForm, individualEvaluations));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private IIndividualEvaluationUpdateWrapper[] getIndividualEvaluationUpdateWrappers(IndividualEvaluationsForm individualEvaluationsForm, List<IndividualEvaluation> individualEvaluations) throws IndividualEvaluationsFormException {
        List<IndividualEvaluationUpdateWrapper> individualEvaluationUpdateWrappers = new ArrayList<>();

        Report report = !individualEvaluations.isEmpty() ? individualEvaluations.get(0).getReport() : null;
        IndividualEvaluationUpdateWrapper individualEvaluationUpdateWrapper;

        for (Map.Entry<String, Float> entry : individualEvaluationsForm.getMarks().entrySet()) {
            individualEvaluationUpdateWrapper = new IndividualEvaluationUpdateWrapper();

            individualEvaluationUpdateWrapper.setWrapped(getIndividualEvaluationToUpdate(entry, report));
            individualEvaluationUpdateWrapper.setReportNumber(report.getNumber());
            individualEvaluationUpdateWrapper.setStudentNumber(entry.getKey());

            individualEvaluationUpdateWrappers.add(individualEvaluationUpdateWrapper);
        }

        return individualEvaluationUpdateWrappers.toArray(new IndividualEvaluationUpdateWrapper[individualEvaluationUpdateWrappers.size()]);
    }

    private IndividualEvaluation getIndividualEvaluationToUpdate(Map.Entry<String, Float> entry, Report report) throws IndividualEvaluationsFormException {
        IndividualEvaluation individualEvaluationToUpdate = new IndividualEvaluationBaseBuilder(entry.getKey()).build();

        individualEvaluationToUpdate.setReport(report);
        individualEvaluationToUpdate.setMark(entry.getValue());

        return individualEvaluationToUpdate;
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