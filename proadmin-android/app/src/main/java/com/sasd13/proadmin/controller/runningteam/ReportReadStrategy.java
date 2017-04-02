package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.ReadRequestorStrategy;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.service.IReportService;

import java.util.List;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ReportReadStrategy extends ReadRequestorStrategy {

    private RunningTeamController controller;
    private IReportService service;

    public ReportReadStrategy(RunningTeamController controller, IReportService service) {
        super();

        this.controller = controller;
        this.service = service;
    }

    @Override
    public Object doInBackgroung(Object[] in) {
        return service.read(parameters);
    }

    @Override
    public void onPostExecute(Object out) {
        super.onPostExecute(out);

        controller.onReadReports((List<Report>) out);
    }

    @Override
    public void onCancelled(Object out) {
        super.onCancelled(out);

        controller.display(R.string.message_cancelled);
    }
}
