package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsForm;
import com.sasd13.proadmin.gui.form.IndividualEvaluationsFormException;
import com.sasd13.proadmin.gui.form.LeadEvaluationForm;
import com.sasd13.proadmin.gui.form.ReportForm;
import com.sasd13.proadmin.util.Constants;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.running.IndividualEvaluationBaseBuilder;
import com.sasd13.proadmin.util.builder.running.LeadEvaluationBaseBuilder;
import com.sasd13.proadmin.util.builder.running.ReportBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IReportUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void create(ReportForm reportForm, LeadEvaluationForm leadEvaluationForm, IndividualEvaluationsForm individualEvaluationsForm, RunningTeam runningTeam) {
        taskType = TASKTYPE_CREATE;
        createTask = new CreateTask<>(WSResources.URL_WS_REPORTS, this);

        try {
            report = getReportToCreate(reportForm, runningTeam);

            setLeadEvaluation(leadEvaluationForm, report);
            setIndividualEvaluations(individualEvaluationsForm, report);

            createTask.execute(report);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Report getReportToCreate(ReportForm reportForm, RunningTeam runningTeam) throws FormException {
        Report reportToCreate = new ReportBaseBuilder(
                runningTeam.getRunning().getYear(),
                runningTeam.getRunning().getProject().getCode(),
                runningTeam.getRunning().getTeacher().getNumber(),
                runningTeam.getTeam().getNumber(),
                runningTeam.getAcademicLevel().getCode()).build();

        reportToCreate.setNumber(Constants.REPORT_DEFAULT_NUMBER);
        reportToCreate.setDateMeeting(reportForm.getDateMeeting());
        reportToCreate.setSession(reportForm.getSession());
        reportToCreate.setComment(reportForm.getComment());

        return reportToCreate;
    }

    private void setLeadEvaluation(LeadEvaluationForm leadEvaluationForm, Report report) throws FormException {
        LeadEvaluation leadEvaluationToCreate = new LeadEvaluationBaseBuilder(leadEvaluationForm.getLeader().getNumber()).build();

        leadEvaluationToCreate.setReport(report);
        leadEvaluationToCreate.setPlanningMark(leadEvaluationForm.getPlanningMark());
        leadEvaluationToCreate.setPlanningComment(leadEvaluationForm.getPlanningComment());
        leadEvaluationToCreate.setCommunicationMark(leadEvaluationForm.getCommunicationMark());
        leadEvaluationToCreate.setCommunicationComment(leadEvaluationForm.getCommunicationComment());

        report.setLeadEvaluation(leadEvaluationToCreate);
    }

    private void setIndividualEvaluations(IndividualEvaluationsForm individualEvaluationsForm, Report report) throws IndividualEvaluationsFormException {
        List<IndividualEvaluation> individualEvaluationsToCreate = new ArrayList<>();

        IndividualEvaluation individualEvaluation;

        for (Map.Entry<String, Float> entry : individualEvaluationsForm.getMarks().entrySet()) {
            individualEvaluation = new IndividualEvaluationBaseBuilder(entry.getKey()).build();

            individualEvaluation.setReport(report);
            individualEvaluation.setMark(entry.getValue());

            individualEvaluationsToCreate.add(individualEvaluation);
        }

        report.setIndividualEvaluations(individualEvaluationsToCreate);
    }

    public void update(ReportForm reportForm, Report report) {
        taskType = TASKTYPE_UPDATE;
        updateTask = new UpdateTask<>(WSResources.URL_WS_REPORTS, this);

        try {
            updateTask.execute(getReportUpdateWrapper(reportForm, report));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private IReportUpdateWrapper getReportUpdateWrapper(ReportForm reportForm, Report report) throws FormException {
        IReportUpdateWrapper reportUpdateWrapper = new ReportUpdateWrapper();

        reportUpdateWrapper.setWrapped(getReportToUpdate(reportForm, report));
        reportUpdateWrapper.setNumber(report.getNumber());

        return reportUpdateWrapper;
    }

    private Report getReportToUpdate(ReportForm reportForm, Report report) throws FormException {
        Report reportToUpdate = new ReportBaseBuilder(
                report.getRunningTeam().getRunning().getYear(),
                report.getRunningTeam().getRunning().getProject().getCode(),
                report.getRunningTeam().getRunning().getTeacher().getNumber(),
                report.getRunningTeam().getTeam().getNumber(),
                report.getRunningTeam().getAcademicLevel().getCode()).build();

        reportToUpdate.setNumber(reportForm.getNumber());
        reportToUpdate.setDateMeeting(reportForm.getDateMeeting());
        reportToUpdate.setSession(reportForm.getSession());
        reportToUpdate.setComment(reportForm.getComment());

        return reportToUpdate;
    }

    public void delete(Report report) {
        taskType = TASKTYPE_DELETE;
        deleteTask = new DeleteTask<>(WSResources.URL_WS_REPORTS, this);

        deleteTask.execute(report);
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        switch (taskType) {
            case TASKTYPE_CREATE:
                onCreateTaskSucceeded();
                break;
            case TASKTYPE_UPDATE:
                onUpdateTaskSucceeded();
                break;
            case TASKTYPE_DELETE:
                onDeleteTaskSucceeded();
                break;
        }
    }

    private void onCreateTaskSucceeded() {
        if (!createTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, createTask.getResponseErrors());
        } else {
            serviceCaller.onCreateSucceeded(report);
        }
    }

    private void onUpdateTaskSucceeded() {
        if (!updateTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, updateTask.getResponseErrors());
        } else {
            serviceCaller.onUpdateSucceeded();
        }
    }

    private void onDeleteTaskSucceeded() {
        if (!deleteTask.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, deleteTask.getResponseErrors());
        } else {
            serviceCaller.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}