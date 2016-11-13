package com.sasd13.proadmin.service.member;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.ws.IManageServiceCaller;
import com.sasd13.androidex.ws.rest.CreateTask;
import com.sasd13.androidex.ws.rest.DeleteTask;
import com.sasd13.androidex.ws.rest.UpdateTask;
import com.sasd13.javaex.net.IHttpCallback;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.gui.form.TeamForm;
import com.sasd13.proadmin.util.ServiceCallerUtils;
import com.sasd13.proadmin.util.builder.member.TeamBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.member.ITeamUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

public class TeamManageService implements IHttpCallback {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private IManageServiceCaller<Team> serviceCaller;
    private CreateTask<Team> createTask;
    private UpdateTask<Team> updateTask;
    private DeleteTask<Team> deleteTask;
    private Team team;
    private int taskType;

    public TeamManageService(IManageServiceCaller<Team> serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    public void create(TeamForm teamForm) {
        taskType = TASKTYPE_CREATE;
        createTask = new CreateTask<>(WSResources.URL_WS_TEAMS, this);

        try {
            team = getTeamToCreate(teamForm);

            createTask.execute(team);
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private Team getTeamToCreate(TeamForm teamForm) throws FormException {
        Team teamToCreate = new TeamBaseBuilder(teamForm.getNumber()).build();

        return teamToCreate;
    }

    public void update(TeamForm teamForm, Team team) {
        taskType = TASKTYPE_UPDATE;
        updateTask = new UpdateTask<>(WSResources.URL_WS_TEAMS, this);

        try {
            updateTask.execute(getTeamUpdateWrapper(teamForm, team));
        } catch (FormException e) {
            serviceCaller.onError(e.getResMessage());
        }
    }

    private ITeamUpdateWrapper getTeamUpdateWrapper(TeamForm teamForm, Team team) throws FormException {
        ITeamUpdateWrapper teamUpdateWrapper = new TeamUpdateWrapper();

        teamUpdateWrapper.setWrapped(getTeamToUpdate(teamForm));
        teamUpdateWrapper.setNumber(team.getNumber());

        return teamUpdateWrapper;
    }

    private Team getTeamToUpdate(TeamForm teamForm) throws FormException {
        Team teamToUpdate = new TeamBaseBuilder(teamForm.getNumber()).build();

        return teamToUpdate;
    }

    public void delete(Team team) {
        taskType = TASKTYPE_DELETE;
        deleteTask = new DeleteTask<>(WSResources.URL_WS_TEAMS, this);

        deleteTask.execute(team);
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
            serviceCaller.onCreateSucceeded(team);
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