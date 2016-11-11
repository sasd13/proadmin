package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.ws.IReadServiceCaller;
import com.sasd13.androidex.ws.rest.ReadTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.ws.WSResources;
import com.sasd13.proadmin.wrapper.read.IReadWrapper;
import com.sasd13.proadmin.wrapper.read.running.ReportReadWrapper;

public class ReportReadService implements IHttpCallback {

    private IReadServiceCaller<IReadWrapper<Report>> serviceCaller;
    private ReadTask<Report> readTask;

    public ReportReadService(IReadServiceCaller<IReadWrapper<Report>> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void readReports() {
        readTask = new ReadTask<>(WSResources.URL_WS_REPORTS, this, Report.class);

        readTask.execute();
    }

    public void readReports(RunningTeam runningTeam) {
        readTask = new ReadTask<>(WSResources.URL_WS_REPORTS, this, Report.class);

        readTask.putParameter(EnumParameter.YEAR.getName(), new String[]{String.valueOf(runningTeam.getRunning().getYear())});
        readTask.putParameter(EnumParameter.PROJECT.getName(), new String[]{runningTeam.getRunning().getProject().getCode()});
        readTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{runningTeam.getRunning().getTeacher().getNumber()});
        readTask.putParameter(EnumParameter.TEAM.getName(), new String[]{runningTeam.getTeam().getNumber()});
        readTask.putParameter(EnumParameter.ACADEMICLEVEL.getName(), new String[]{runningTeam.getAcademicLevel().getCode()});
        readTask.execute();
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        if (!readTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, readTask.getResponseErrors());
        } else {
            try {
                serviceCaller.onReadSucceeded(new ReportReadWrapper(readTask.getResults()));
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    @Override
    public void onFail(int i) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}