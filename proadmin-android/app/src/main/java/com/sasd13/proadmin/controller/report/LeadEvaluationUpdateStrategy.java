package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.RequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.service.ILeadEvaluationService;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LeadEvaluationUpdateStrategy extends RequestorStrategy {

    private ReportController controller;
    private ILeadEvaluationService service;

    public LeadEvaluationUpdateStrategy(ReportController controller, ILeadEvaluationService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        service.update((LeadEvaluationUpdateWrapper) in[0]);

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
