package com.sasd13.proadmin.controller.report;

import android.content.Context;

import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.WebServiceUtils;
import com.sasd13.proadmin.util.wrapper.ReportDependencyWrapper;
import com.sasd13.proadmin.ws.service.ReportDependencyService;
import com.sasd13.proadmin.ws.service.ReportService;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public class ReportServiceCaller implements ReportService.Caller, ReportDependencyService.RetrieveCaller {

    private ReportController controller;
    private Context context;

    public ReportServiceCaller(ReportController controller, Context context) {
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
    public void onRetrieved(ReportDependencyWrapper dependencyWrapper) {
        controller.onRetrieved(dependencyWrapper);
    }

    @Override
    public void onCreated() {
        controller.displayMessage(context.getString(R.string.message_saved));
        controller.backPress();
    }

    @Override
    public void onUpdated() {
        controller.displayMessage(context.getString(R.string.message_updated));
    }

    @Override
    public void onDeleted() {
        controller.displayMessage(context.getString(R.string.message_deleted));
        controller.backPress();
    }

    @Override
    public void onErrors(List<String> errors) {
        controller.displayMessage(WebServiceUtils.handleErrors(context, errors));
    }
}