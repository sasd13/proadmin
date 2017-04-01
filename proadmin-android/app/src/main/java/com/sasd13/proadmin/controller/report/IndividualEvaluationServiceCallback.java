package com.sasd13.proadmin.controller.report;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.service.impl.IndividualEvaluationService;
import com.sasd13.proadmin.util.WebServiceUtils;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class IndividualEvaluationServiceCallback implements IndividualEvaluationService.Callback {

    private ReportController controller;
    private Context context;

    public IndividualEvaluationServiceCallback(ReportController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onPreExecute() {
    }

    @Override
    public void onReaded(List<IndividualEvaluation> individualEvaluations) {
        //Do nothing
    }

    @Override
    public void onCreated() {
        controller.displayMessage(context.getString(R.string.message_saved));
    }

    @Override
    public void onUpdated() {
        controller.displayMessage(context.getString(R.string.message_saved));
    }

    @Override
    public void onDeleted() {
        controller.displayMessage(context.getString(R.string.message_deleted));
        controller.entry();
    }

    @Override
    public void onErrors(List<String> errors) {
        controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}
