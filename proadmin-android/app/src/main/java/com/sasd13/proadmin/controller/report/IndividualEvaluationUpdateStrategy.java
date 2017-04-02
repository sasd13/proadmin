package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.service.IIndividualEvaluationService;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class IndividualEvaluationUpdateStrategy extends RequestorStrategy {

    private ReportController controller;
    private IIndividualEvaluationService service;

    public IndividualEvaluationUpdateStrategy(ReportController controller, IIndividualEvaluationService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object in) {
        Map<Class, List> allIndividualEvaluations = (Map<Class, List>) in[0];
        List<IndividualEvaluation> individualEvaluationsToCreate = allIndividualEvaluations.get(IndividualEvaluation.class);
        List<IndividualEvaluationUpdateWrapper> individualEvaluationsToUpdate = allIndividualEvaluations.get(IndividualEvaluationUpdateWrapper.class);

        if (!individualEvaluationsToCreate.isEmpty()) {
            service.create(individualEvaluationsToCreate);
        }

        if (!individualEvaluationsToUpdate.isEmpty()) {
            service.update(individualEvaluationsToUpdate);
        }

        return null;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.display(R.string.message_updated);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
