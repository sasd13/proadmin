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
import com.sasd13.proadmin.ws.WSInformation;

import java.util.List;

public class RunningService implements IWSPromise {

    private static final int TASKTYPE_CREATE = 0;
    private static final int TASKTYPE_UPDATE = 1;
    private static final int TASKTYPE_DELETE = 2;

    private RunningFragment runningFragment;
    private DefaultRunningBuilder defaultRunningBuilder;
    private CreateTask<Running> createTask;
    private UpdateTask<Running> updateTask;
    private DeleteTask<Running> deleteTask;
    private Running running;
    private int taskType;

    public RunningService(RunningFragment runningFragment) {
        this.runningFragment = runningFragment;
        defaultRunningBuilder = new DefaultRunningBuilder();
    }

    public List<Project> readProjectsFromCache() {
        return null;
    }

    public Running getDefaultValueOfRunning() {
        return defaultRunningBuilder.build();
    }

    public void createRunning(RunningForm runningForm) {
        taskType = TASKTYPE_CREATE;

        try {
            running = getRunningToCreate(runningForm);
            createTask = new CreateTask<>(WSInformation.URL_WS_RUNNINGS, this);

            createTask.execute(running);
        } catch (FormException e) {
            runningFragment.onError(e.getResMessage());
        }
    }

    private Running getRunningToCreate(RunningForm runningForm) throws FormException {
        Running runningFromForm = runningForm.getEditable();
        Running runningToCreate = new Running();

        Binder.bind(runningToCreate, runningFromForm);

        //runningToCreate.setTeacher();

        return runningToCreate;
    }

    public void updateRunning(Running runningToUpdate, RunningForm runningForm) {
        taskType = TASKTYPE_UPDATE;

        try {
            Running runningFromForm = runningForm.getEditable();

            Binder.bind(runningToUpdate, runningFromForm);

            running = runningToUpdate;
            //running.setTeacher();

            updateTask = new UpdateTask<>(WSInformation.URL_WS_RUNNINGS, this);
            updateTask.execute(running);
        } catch (FormException e) {
            runningFragment.onError(e.getResMessage());
        }
    }

    public void deleteRunning(Running running) {
        taskType = TASKTYPE_DELETE;
        this.running = running;
        deleteTask = new DeleteTask<>(WSInformation.URL_WS_RUNNINGS, this);

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
        if (createTask.hasResponseWSErrors()) {
            //TODO : manage WS errors
        } else {
            try {
                runningFragment.onCreateSucceeded();
            } catch (IndexOutOfBoundsException e) {
                runningFragment.onError(R.string.ws_error_data_retrieval_error);
            }
        }
    }

    private void onUpdateTaskSucceeded() {
        if (updateTask.hasResponseWSErrors()) {
            //TODO : manage WS errors
        } else {
            runningFragment.onUpdateSucceeded();
        }
    }

    private void onDeleteTaskSucceeded() {
        if (deleteTask.hasResponseWSErrors()) {
            //TODO : manage WS errors
        } else {
            runningFragment.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        runningFragment.onError(R.string.ws_error_server_connection_failed);
    }
}