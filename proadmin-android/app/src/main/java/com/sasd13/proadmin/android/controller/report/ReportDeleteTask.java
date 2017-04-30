package com.sasd13.proadmin.android.controller.report;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.service.v1.IReportService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportDeleteTask extends RequestorTask {

    private ReportController controller;
    private IReportService service;

    public ReportDeleteTask(ReportController controller, IReportService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.delete((List<Report>) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onDeleteReport();
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
