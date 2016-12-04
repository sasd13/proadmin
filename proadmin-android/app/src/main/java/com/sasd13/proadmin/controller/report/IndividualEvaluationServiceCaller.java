package com.sasd13.proadmin.controller.report;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.ws.service.IndividualEvaluationService;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class IndividualEvaluationServiceCaller implements IndividualEvaluationService.Caller {

    private ReportController controller;
    private Context context;

    public IndividualEvaluationServiceCaller(ReportController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<IndividualEvaluation> individualEvaluations) {
        controller.onReadIndividualEvaluations(individualEvaluations);
    }

    @Override
    public void onCreated() {
        controller.displayMessage(context.getString(R.string.message_saved));
        controller.backPress();
    }

    @Override
    public void onUpdated() {
        controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onDeleted() {
        controller.displayMessage(context.getString(R.string.message_deleted));
        controller.backPress();
    }

    @Override
    public void onErrors(List<String> errors) {
        controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
