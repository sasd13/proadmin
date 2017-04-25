package com.sasd13.proadmin.android.controller.report;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportReadTask extends ReadRequestorTask {

    private ReportController controller;
    private IReportService service;

    public ReportReadTask(ReportController controller, IReportService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        ServiceResult<List<Report>> result = (ServiceResult<List<Report>>) out;

        if (result.isSuccess()) {
            controller.onReadReports(result.getData());
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
