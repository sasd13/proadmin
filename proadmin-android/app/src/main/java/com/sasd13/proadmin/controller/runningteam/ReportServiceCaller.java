package com.sasd13.proadmin.controller.runningteam;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.service.ws.ReportService;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class ReportServiceCaller implements ReportService.Caller {

    private RunningTeamController controller;
    private Context context;

    public ReportServiceCaller(RunningTeamController controller, Context context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void onWaiting() {
    }

    @Override
    public void onReaded(List<Report> reports) {
        controller.onReadReports(reports);
    }

    @Override
    public void onCreated() {
        controller.displayMessage(context.getString(R.string.message_saved));
        controller.entry();
    }

    @Override
    public void onUpdated() {
        controller.displayMessage(context.getString(R.string.message_updated));
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
