package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ManagePromise;
import com.sasd13.androidex.ws.rest.promise.ReadPromise;
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

    public interface Callback extends ReadPromise.Callback<Report>, ManagePromise.Callback {
    }

    private ReadPromise<Report> readPromise;
    private ManagePromise<Report> managePromise;

    public ReportService(Callback callback) {
        readPromise = new ReadPromise<>(callback, WSResources.URL_WS_REPORTS, Report.class);
        managePromise = new ManagePromise<>(callback, WSResources.URL_WS_REPORTS);
    }

    public void readByNumber(String number) {
        readPromise.clearHeaders();
        readPromise.clearParameters();
        readPromise.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readPromise.putParameters(EnumParameter.NUMBER.getName(), new String[]{number});
        readPromise.read();
    }

    public void readByTeacher(String teacherNumber) {
        readPromise.clearHeaders();
        readPromise.clearParameters();
        readPromise.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readPromise.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readPromise.read();
    }

    public void readByRunningTeam(String teacherNumber, int year, String projectCode, String teamNumber, String academicLevelCode) {
        readPromise.clearHeaders();
        readPromise.clearParameters();
        readPromise.putHeaders(EnumHttpHeader.READ_CODE.getName(), new String[]{Constants.WS_REQUEST_READ_DEEP});
        readPromise.putParameters(EnumParameter.YEAR.getName(), new String[]{String.valueOf(year)});
        readPromise.putParameters(EnumParameter.PROJECT.getName(), new String[]{projectCode});
        readPromise.putParameters(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        readPromise.putParameters(EnumParameter.TEAM.getName(), new String[]{teamNumber});
        readPromise.putParameters(EnumParameter.ACADEMICLEVEL.getName(), new String[]{academicLevelCode});
        readPromise.read();
    }

    public void create(Report report) {
        managePromise.create(report);
    }

    public void update(Report report, Report reportToUpdate) {
        managePromise.update(getUpdateWrapper(report, reportToUpdate));
    }

    private ReportUpdateWrapper getUpdateWrapper(Report report, Report reportToUpdate) {
        ReportUpdateWrapper updateWrapper = new ReportUpdateWrapper();

        updateWrapper.setWrapped(report);
        updateWrapper.setNumber(reportToUpdate.getNumber());

        return updateWrapper;
    }

    public void delete(Report report) {
        managePromise.delete(report);
    }
}
