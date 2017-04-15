package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.ReadRequestorTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumErrorRes;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportReadTask extends ReadRequestorTask {

    private RunningTeamController controller;
    private IReportService service;

    public ReportReadTask(RunningTeamController controller, IReportService service) {
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

        if (((ServiceResult) out).isSuccess()) {
            controller.onReadReports(((ServiceResult<List<Report>>) out).getResult());
        } else {
            controller.display(EnumErrorRes.find(((ServiceResult) out).getHttpStatus()).getResID());
        }
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
