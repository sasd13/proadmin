package com.sasd13.proadmin.android.controller.report;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.update.IndividualEvaluationUpdate;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class IndividualEvaluationUpdateTask extends RequestorTask {

    private ReportController controller;
    private IIndividualEvaluationService service;

    public IndividualEvaluationUpdateTask(ReportController controller, IIndividualEvaluationService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        ServiceResult result = ServiceResult.NULL;

        Map<Class, List> allIndividualEvaluations = ((Map<Class, List>) in);
        List<IndividualEvaluation> individualEvaluationsToCreate = allIndividualEvaluations.get(IndividualEvaluation.class);
        List<IndividualEvaluationUpdate> individualEvaluationsToUpdate = allIndividualEvaluations.get(IndividualEvaluationUpdate.class);

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
            controller.onFail(result.getHttpStatus(), result.getHeaders().get(EnumHttpHeader.RESPONSE_ERROR.getName()));
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.onCancelled();
    }
}
