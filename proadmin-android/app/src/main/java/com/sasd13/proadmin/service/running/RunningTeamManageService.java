package com.sasd13.proadmin.service.running;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.gui.form.RunningTeamForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.running.RunningTeamBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class RunningTeamManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE_RUNNING = 0;
    private static final int TASKTYPE_CREATE_RUNNINGTEAM = 1;
    private static final int TASKTYPE_UPDATE_RUNNINGTEAM = 2;
    private static final int TASKTYPE_DELETE_RUNNINGTEAM = 3;

    private IManageServiceCaller<RunningTeam> serviceCaller;
    private CreateTask<Team> createTaskTeam;
    private CreateTask<RunningTeam> createTaskRunningTeam;
    private UpdateTask<RunningTeam> updateTaskRunningTeam;
    private DeleteTask<RunningTeam> deleteTaskRunningTeam;
    private RunningTeam runningTeam;
    private int taskType;

    public RunningTeamManageService(IManageServiceCaller<RunningTeam> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void createTeam(RunningTeamForm runningTeamForm, String teacherNumber) {
        taskType = TASKTYPE_CREATE_RUNNING;

        try {
            runningTeam = getRunningTeamToCreate(runningTeamForm, teacherNumber);
            createTaskTeam = new CreateTask<>(WSResources.URL_WS_TEAMS, this);

            createTaskTeam.execute(runningTeam.getTeam());
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private RunningTeam getRunningTeamToCreate(RunningTeamForm runningTeamForm, String teacherNumber) throws FormException {
        RunningTeam runningTeamToCreate = new RunningTeamBaseBuilder(
                runningTeamForm.getYear(),
                runningTeamForm.getProject().getCode(),
                teacherNumber,
                runningTeamForm.getTeam().getNumber(),
                runningTeamForm.getAcademicLevel().getCode()).build();

        return runningTeamToCreate;
    }

    public void updateRunningTeam(RunningTeamForm runningTeamForm, RunningTeam runningTeam) {
        taskType = TASKTYPE_UPDATE_RUNNINGTEAM;

        try {
            updateTaskRunningTeam = new UpdateTask<>(WSResources.URL_WS_RUNNINGTEAMS, this);

            updateTaskRunningTeam.execute(getRunningTeamUpdateWrapper(runningTeamForm, runningTeam));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private IRunningTeamUpdateWrapper getRunningTeamUpdateWrapper(RunningTeamForm runningTeamForm, RunningTeam runningTeam) throws FormException {
        IRunningTeamUpdateWrapper runningTeamUpdateWrapper = new RunningTeamUpdateWrapper();

        runningTeamUpdateWrapper.setWrapped(getRunningTeamToUpdate(runningTeamForm, runningTeam.getRunning().getTeacher().getNumber()));
        runningTeamUpdateWrapper.setRunningYear(runningTeam.getRunning().getYear());
        runningTeamUpdateWrapper.setProjectCode(runningTeam.getRunning().getProject().getCode());
        runningTeamUpdateWrapper.setTeacherNumber(runningTeam.getRunning().getTeacher().getNumber());
        runningTeamUpdateWrapper.setTeamNumber(runningTeam.getTeam().getNumber());
        runningTeamUpdateWrapper.setAcademicLevelCode(runningTeam.getAcademicLevel().getCode());

        return runningTeamUpdateWrapper;
    }

    private RunningTeam getRunningTeamToUpdate(RunningTeamForm runningTeamForm, String teacherNumber) throws FormException {
        RunningTeam runningTeamToUpdate = new RunningTeamBaseBuilder(
                runningTeamForm.getYear(),
                runningTeamForm.getProject().getCode(),
                teacherNumber,
                runningTeamForm.getTeam().getNumber(),
                runningTeamForm.getAcademicLevel().getCode()).build();

        return runningTeamToUpdate;
    }

    public void deleteRunningTeam(RunningTeam runningTeam) {
        taskType = TASKTYPE_DELETE_RUNNINGTEAM;
        deleteTaskRunningTeam = new DeleteTask<>(WSResources.URL_WS_RUNNINGTEAMS, this);

        deleteTaskRunningTeam.execute(runningTeam);
    }

    @Override
    public void onLoad() {
        serviceCaller.onLoad();
    }

    @Override
    public void onSuccess() {
        switch (taskType) {
            case TASKTYPE_CREATE_RUNNING:
                onCreateTeamTaskSucceeded();
                break;
            case TASKTYPE_CREATE_RUNNINGTEAM:
                onCreateRunningTeamTaskSucceeded();
                break;
            case TASKTYPE_UPDATE_RUNNINGTEAM:
                onUpdateRunningTeamTaskSucceeded();
                break;
            case TASKTYPE_DELETE_RUNNINGTEAM:
                onDeleteRunningTeamTaskSucceeded();
                break;
        }
    }

    private void onCreateTeamTaskSucceeded() {
        if (!createTaskTeam.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, createTaskTeam.getResponseErrors());
        } else {
            taskType = TASKTYPE_CREATE_RUNNINGTEAM;
            createTaskRunningTeam = new CreateTask<>(WSResources.URL_WS_RUNNINGTEAMS, this);

            createTaskRunningTeam.execute(runningTeam);
        }
    }

    private void onCreateRunningTeamTaskSucceeded() {
        if (!createTaskRunningTeam.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, createTaskRunningTeam.getResponseErrors());
        } else {
            try {
                serviceCaller.onCreateSucceeded(runningTeam);
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    private void onUpdateRunningTeamTaskSucceeded() {
        if (!updateTaskRunningTeam.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, updateTaskRunningTeam.getResponseErrors());
        } else {
            try {
                serviceCaller.onUpdateSucceeded();
            } catch (IndexOutOfBoundsException e) {
                serviceCaller.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    private void onDeleteRunningTeamTaskSucceeded() {
        if (!deleteTaskRunningTeam.getResponseErrors().isEmpty()) {
            ServiceCallerUtils.handleErrors(serviceCaller, deleteTaskRunningTeam.getResponseErrors());
        } else {
            serviceCaller.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        serviceCaller.onError(R.string.error_ws_server_connection);
    }
}