package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.javaex.net.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.service.ILeadEvaluationService;
import com.sasd13.proadmin.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class LeadEvaluationCreateTask extends RequestorTask {

    private ReportController controller;
    private ILeadEvaluationService service;

    public LeadEvaluationCreateTask(ReportController controller, ILeadEvaluationService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.create((LeadEvaluation) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onCreateLeadEvaluation();
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
