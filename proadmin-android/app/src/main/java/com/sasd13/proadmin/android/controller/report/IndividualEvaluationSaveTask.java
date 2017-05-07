package com.sasd13.proadmin.android.controller.report;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class IndividualEvaluationSaveTask extends RequestorTask {

    private ReportController controller;
    private IIndividualEvaluationService service;

    public IndividualEvaluationSaveTask(ReportController controller, IIndividualEvaluationService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        ServiceResult result = ServiceResult.NULL;

        Map<String, List> allIndividualEvaluations = (Map<String, List>) in;
        List<IndividualEvaluation> individualEvaluationsToUpdate = (List<IndividualEvaluation>) allIndividualEvaluations.get(ReportController.INDIVIDUALEVALUATIONS_TO_UPDATE);
        List<IndividualEvaluation> individualEvaluationsToCreate = (List<IndividualEvaluation>) allIndividualEvaluations.get(ReportController.INDIVIDUALEVALUATIONS_TO_CREATE);

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
