package com.sasd13.proadmin.android.controller.report;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.bean.update.ReportUpdate;
import com.sasd13.proadmin.android.service.v1.IReportService;
import com.sasd13.proadmin.android.service.ServiceResult;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportUpdateTask extends RequestorTask {

    private ReportController controller;
    private IReportService service;

    public ReportUpdateTask(ReportController controller, IReportService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.update((ReportUpdate) in);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<?> result = (ServiceResult<?>) out;

        if (result.isSuccess()) {
            controller.onUpdateReport();
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
