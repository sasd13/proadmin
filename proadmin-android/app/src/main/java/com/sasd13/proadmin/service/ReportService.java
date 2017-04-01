package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class ReportService {

    private Promise promiseRead, promiseCreate, promiseUpdate, promiseDelete;
    private Map<String, String[]> parameters;

    public ReportService() {
        promiseRead = new Promise("GET", WSResources.URL_WS_REPORTS, Report.class);
        promiseCreate = new Promise("POST", WSResources.URL_WS_REPORTS);
        promiseUpdate = new Promise("PUT", WSResources.URL_WS_REPORTS);
        promiseDelete = new Promise("DELETE", WSResources.URL_WS_REPORTS);
        parameters = new HashMap<>();

        promiseRead.setParameters(parameters);
    }

    public void readByNumber(ICallback callback, String number) {
        parameters.clear();
        parameters.put(EnumParameter.NUMBER.getName(), new String[]{number});
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void readByTeacher(ICallback callback, String teacherNumber) {
        parameters.clear();
        parameters.put(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void readByRunningTeam(ICallback callback, String teacherNumber, int year, String projectCode, String teamNumber, String academicLevelCode) {
        parameters.clear();
        parameters.put(EnumParameter.YEAR.getName(), new String[]{String.valueOf(year)});
        parameters.put(EnumParameter.PROJECT.getName(), new String[]{projectCode});
        parameters.put(EnumParameter.TEACHER.getName(), new String[]{teacherNumber});
        parameters.put(EnumParameter.TEAM.getName(), new String[]{teamNumber});
        parameters.put(EnumParameter.ACADEMICLEVEL.getName(), new String[]{academicLevelCode});
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void create(ICallback callback, Report report) {
        promiseCreate.registerCallback(callback);
        promiseCreate.execute(report);
    }

    public void update(ICallback callback, Report report, Report reportToUpdate) {
        promiseUpdate.registerCallback(callback);
        promiseUpdate.execute(getUpdateWrapper(report, reportToUpdate));
    }

    private ReportUpdateWrapper getUpdateWrapper(Report report, Report reportToUpdate) {
        ReportUpdateWrapper updateWrapper = new ReportUpdateWrapper();

        updateWrapper.setWrapped(report);
        updateWrapper.setNumber(reportToUpdate.getNumber());

        return updateWrapper;
    }

    public void delete(ICallback callback, Report report) {
        promiseDelete.registerCallback(callback);
        promiseDelete.execute(report);
    }
}
