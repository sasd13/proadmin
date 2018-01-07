package com.sasd13.proadmin.android.controller.runningteam;

import com.sasd13.androidex.util.requestor.RequestorTask;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportReadTask extends RequestorTask {

    private RunningTeamController controller;
    private IReportService service;

    public ReportReadTask(RunningTeamController controller, IReportService service) {
        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object execute(Object in) {
        return service.read((Map<String, Object>) in);
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
