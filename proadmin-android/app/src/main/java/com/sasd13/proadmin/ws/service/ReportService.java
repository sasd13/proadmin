package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ReportService {

    public interface Caller extends ReadService.Caller<Report>, ManageService.Caller {
    }

    private ReadService<Report> readService;
    private ManageService<Report> manageService;

    public ReportService(Caller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_REPORTS, Report.class);
        manageService = new ManageService<>(caller, WSResources.URL_WS_REPORTS);
    }

    public void read(String teacherNumber) {
        readService.clearHeaders();
        readService.clearParameters();
        readService.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readService.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readService.read();
    }

    public void read(String teacherNumber, int year, String projectCode, String teamNumber, String academicLevelCode) {
        readService.clearHeaders();
        readService.clearParameters();
        readService.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readService.putParameters(EnumParameter.YEAR.getName(), new String[]{String.valueOf(year)});
        readService.putParameters(EnumParameter.PROJECT.getName(), new String[]{projectCode});
        readService.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readService.putParameters(EnumParameter.TEAM.getName(), new String[]{teamNumber});
        readService.putParameters(EnumParameter.ACADEMICLEVEL.getName(), new String[]{academicLevelCode});
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
