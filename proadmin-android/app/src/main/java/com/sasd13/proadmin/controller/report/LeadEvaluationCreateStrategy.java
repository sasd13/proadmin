package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.service.ILeadEvaluationService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LeadEvaluationCreateStrategy extends RequestorStrategy {

    private ReportController controller;
    private ILeadEvaluationService service;

    public LeadEvaluationCreateStrategy(ReportController controller, ILeadEvaluationService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object in) {
        service.create((LeadEvaluation) in[0]);

        return null;
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.display(R.string.message_saved);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
