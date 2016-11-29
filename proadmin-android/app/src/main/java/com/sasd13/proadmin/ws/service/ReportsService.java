package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ReportsService {

    public interface ReadCaller extends ReadService.Caller<Report> {
    }

    public interface ManageCaller extends ManageService.Caller {
    }

    private ReadService<Report> readService;
    private ManageService<Report> manageService;

    public ReportsService(ReadCaller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_REPORTS, Report.class);
    }

    public ReportsService(ManageCaller caller) {
        manageService = new ManageService<>(caller, WSResources.URL_WS_REPORTS);
    }

    public void read(String teacherNumber) {
        readService.clearHeaders();
        readService.clearParameters();
        readService.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readService.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readService.read();
    }

    public void read(RunningTeam runningTeam) {
        readService.clearHeaders();
        readService.clearParameters();
        readService.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readService.putParameters(EnumParameter.YEAR.getName(), new String[]{String.valueOf(runningTeam.getRunning().getYear())});
        readService.putParameters(EnumParameter.PROJECT.getName(), new String[]{runningTeam.getRunning().getProject().getCode()});
        readService.putParameters(EnumParameter.TEACHER.getName(), new String[]{runningTeam.getRunning().getTeacher().getNumber()});
        readService.putParameters(EnumParameter.TEAM.getName(), new String[]{runningTeam.getTeam().getNumber()});
        readService.putParameters(EnumParameter.ACADEMICLEVEL.getName(), new String[]{runningTeam.getAcademicLevel().getCode()});
        readService.read();
    }

    public void create(Report report) {
        manageService.create(report);
    }

    public void update(Report report, Report reportToUpdate) {
        manageService.update(getUpdateWrapper(report, reportToUpdate));
    }

    private ReportUpdateWrapper getUpdateWrapper(Report report, Report reportToUpdate) {
        ReportUpdateWrapper updateWrapper = new ReportUpdateWrapper();

        updateWrapper.setWrapped(report);
        updateWrapper.setNumber(reportToUpdate.getNumber());

        return updateWrapper;
    }

    public void delete(Report report) {
        manageService.delete(report);
    }
}
