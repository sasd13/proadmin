package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.gui.form.ReportForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.running.ReportBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class ReportManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private IManageServiceCaller<Report> serviceCaller;
    private CreateTask<Report> createTask;
    private UpdateTask<Report> updateTask;
    private DeleteTask<Report> deleteTask;
    private Report report;
    private int taskType;

    public ReportManageService(IManageServiceCaller<Report> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void createTeam(ReportForm reportForm, String teacherNumber) {
        taskType = TASKTYPE_CREATE;

        try {
            report = getReportToCreate(reportForm, teacherNumber);
            createTaskTeam = new CreateTask<>(WSResources.URL_WS_TEAMS, this);

            createTaskTeam.execute(report.getTeam());
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Report getReportToCreate(ReportForm reportForm, String teacherNumber) throws FormException {
        Report reportToCreate = new ReportBaseBuilder(
                reportForm.getYear(),
                reportForm.getProject().getCode(),
                teacherNumber,
                reportForm.getTeam().getNumber(),
                reportForm.getAcademicLevel().getCode()).build();

        return reportToCreate;
    }

    public void updateReport(ReportForm reportForm, Report report) {
        taskType = TASKTYPE_UPDATE;

        try {
            updateTaskReport = new UpdateTask<>(WSResources.URL_WSS, this);

            updateTaskReport.execute(getReportUpdateWrapper(reportForm, report));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private IReportUpdateWrapper getReportUpdateWrapper(ReportForm reportForm, Report report) throws FormException {
        IReportUpdateWrapper reportUpdateWrapper = new ReportUpdateWrapper();

        reportUpdateWrapper.setWrapped(getReportToUpdate(reportForm, report.getRunning().getTeacher().getNumber()));
        reportUpdateWrapper.setRunningYear(report.getRunning().getYear());
        reportUpdateWrapper.setProjectCode(report.getRunning().getProject().getCode());
        reportUpdateWrapper.setTeacherNumber(report.getRunning().getTeacher().getNumber());
        reportUpdateWrapper.setTeamNumber(report.getTeam().getNumber());
        reportUpdateWrapper.setAcademicLevelCode(report.getAcademicLevel().getCode());

        return reportUpdateWrapper;
    }

    private Report getReportToUpdate(ReportForm reportForm, String teacherNumber) throws FormException {
        Report reportToUpdate = new ReportBaseBuilder(
                reportForm.getYear(),
                reportForm.getProject().getCode(),
                teacherNumber,
                reportForm.getTeam().getNumber(),
                reportForm.getAcademicLevel().getCode()).build();

        return reportToUpdate;
    }

    public void deleteReport(Report report) {
        taskType = TASKTYPE_DELETE;
        deleteTaskReport = new DeleteTask<>(WSResources.URL_WSS, this);

        deleteTaskReport.execute(report);
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        switch (taskType) {
            case TASKTYPE_CREATE:
                onCreateTeamTaskSucceeded();
                break;
            case TASKTYPE_CREATE:
                onCreateReportTaskSucceeded();
                break;
            case TASKTYPE_UPDATE:
                onUpdateReportTaskSucceeded();
                break;
            case TASKTYPE_DELETE:
                onDeleteReportTaskSucceeded();
                break;
        }
    }

    private void onCreateTeamTaskSucceeded() {
        if (!createTaskTeam.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, createTaskTeam.getResponseErrors());
        } else {
            taskType = TASKTYPE_CREATE;
            createTaskReport = new CreateTask<>(WSResources.URL_WSS, this);

            createTaskReport.execute(report);
        }
    }

    private void onCreateReportTaskSucceeded() {
        if (!createTaskReport.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, createTaskReport.getResponseErrors());
        } else {
            try {
                serviceCaller.onCreateSucceeded(report);
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    private void onUpdateReportTaskSucceeded() {
        if (!updateTaskReport.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, updateTaskReport.getResponseErrors());
        } else {
            try {
                serviceCaller.onUpdateSucceeded();
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    private void onDeleteReportTaskSucceeded() {
        if (!deleteTaskReport.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, deleteTaskReport.getResponseErrors());
        } else {
            serviceCaller.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}