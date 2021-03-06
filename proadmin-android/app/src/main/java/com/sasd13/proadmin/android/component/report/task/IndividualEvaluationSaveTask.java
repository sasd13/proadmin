package com.sasd13.proadmin.android.component.report.task;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.component.report.controller.ReportController;
import com.sasd13.proadmin.android.model.IndividualEvaluation;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.wrapper.IndividualEvaluationSaveWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class IndividualEvaluationSaveTask extends RequestorTask {

    private ReportController controller;
    private IIndividualEvaluationService service;

    public IndividualEvaluationSaveTask(ReportController controller, IIndividualEvaluationService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        ServiceResult result = ServiceResult.NULL;

        IndividualEvaluationSaveWrapper wrapper = (IndividualEvaluationSaveWrapper) in;
        List<IndividualEvaluation> individualEvaluationsToUpdate = wrapper.getIndividualEvaluationsToUpdate();
        List<IndividualEvaluation> individualEvaluationsToCreate = wrapper.getIndividualEvaluationsToCreate();

        if (!individualEvaluationsToUpdate.isEmpty()) {
            result = service.update(individualEvaluationsToUpdate);
        }

        if (!individualEvaluationsToCreate.isEmpty()) {
            result = service.create(individualEvaluationsToCreate);
        }

        return result;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onUpdateIndividualEvaluations();
        } else {
            controller.onFail(result.getHttpStatus(), result.getErrors());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
