package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.IWSPromise;
import com.sasd13.androidex.ws.rest.task.CreateTask;
import com.sasd13.androidex.ws.rest.task.DeleteTask;
import com.sasd13.androidex.ws.rest.task.UpdateTask;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activities.fragments.running.RunningFragment;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.form.FormException;
import com.sasd13.proadmin.form.RunningForm;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumErrorRes;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.List;

public class RunningService implements IWSPromise {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private RunningFragment runningFragment;
    private CreateTask<Running> createTask;
    private UpdateTask<Running> updateTask;
    private DeleteTask<Running> deleteTask;
    private Running running;
    private int taskType;

    public RunningService(RunningFragment runningFragment) {
        this.runningFragment = runningFragment;
    }

    public List<Project> readProjectsFromCache() {
        return null;
    }

    public Running getDefaultValueOfRunning(Project project) {
        return new DefaultRunningBuilder(project).build();
    }

    public void createRunning(RunningForm runningForm, Project project) {
        taskType = TASKTYPE_CREATE;

        try {
            running = getRunningToCreate(runningForm, project);
            createTask = new CreateTask<>(WSResources.URL_WS_RUNNINGS, this);

            createTask.execute(running);
        } catch (FormException e) {
            runningFragment.onError(e.getResMessage());
        }
    }

    private Running getRunningToCreate(RunningForm runningForm, Project project) throws FormException {
        Running runningFromForm = runningForm.getEditable();
        Running runningToCreate = new Running(project);

        Binder.bind(runningToCreate, runningFromForm);

        //runningToCreate.setTeacher();

        return runningToCreate;
    }

    public void updateRunning(RunningForm runningForm, Running runningToUpdate) {
        taskType = TASKTYPE_UPDATE;

        try {
            Running runningFromForm = runningForm.getEditable();

            Binder.bind(runningToUpdate, runningFromForm);

            running = runningToUpdate;
            //running.setTeacher();

            updateTask = new UpdateTask<>(WSResources.URL_WS_RUNNINGS, this);
            updateTask.execute(running);
        } catch (FormException e) {
            runningFragment.onError(e.getResMessage());
        }
    }

    public void deleteRunning(Running running) {
        taskType = TASKTYPE_DELETE;
        this.running = running;
        deleteTask = new DeleteTask<>(WSResources.URL_WS_RUNNINGS, this);

        deleteTask.execute(running);
    }

    @Override
    public void onLoad() {
        runningFragment.onLoad();
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
        if (createTask.hasResponseErrors()) {
            EnumError error = EnumError.find(Integer.parseInt(createTask.getResponseErrors().get(0)));

            runningFragment.onError(EnumErrorRes.find(error).getStringRes());
        } else {
            try {
                runningFragment.onCreateSucceeded();
            } catch (IndexOutOfBoundsException e) {
                runningFragment.onError(R.string.error_ws_retrieve_data);
            }
        }
    }

    private void onUpdateTaskSucceeded() {
        if (updateTask.hasResponseErrors()) {
            EnumError error = EnumError.find(Integer.parseInt(updateTask.getResponseErrors().get(0)));

            runningFragment.onError(EnumErrorRes.find(error).getStringRes());
        } else {
            runningFragment.onUpdateSucceeded();
        }
    }

    private void onDeleteTaskSucceeded() {
        if (deleteTask.hasResponseErrors()) {
            EnumError error = EnumError.find(Integer.parseInt(deleteTask.getResponseErrors().get(0)));

            runningFragment.onError(EnumErrorRes.find(error).getStringRes());
        } else {
            runningFragment.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        runningFragment.onError(R.string.error_ws_server_connection);
    }
}