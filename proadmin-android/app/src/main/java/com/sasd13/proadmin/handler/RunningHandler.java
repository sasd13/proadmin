package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.CreateTask;
import com.sasd13.androidex.net.ws.rest.task.DeleteTask;
import com.sasd13.androidex.net.ws.rest.task.UpdateTask;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.form.FormException;
import com.sasd13.proadmin.form.RunningForm;
import com.sasd13.proadmin.fragment.running.RunningFragment;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumWSCodeRes;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.ws.EnumWSCode;
import com.sasd13.proadmin.ws.WSInformation;

import java.util.List;

public class RunningHandler implements IWSPromise {

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

    public RunningHandler(RunningFragment runningFragment) {
        this.runningFragment = runningFragment;
        defaultRunningBuilder = new DefaultRunningBuilder();
    }

    public List<Project> readProjectsFromCache() {
        return Cache.loadAll(runningFragment.getContext(), Project.class);
    }

    public Running getDefaultValueOfRunning() {
        return defaultRunningBuilder.build();
    }

    public void createRunning(RunningForm runningForm, Project project) {
        taskType = TASKTYPE_CREATE;

        try {
            running = getRunningToCreate(runningForm, project);
            createTask = new CreateTask<>(Running.class, WSInformation.URL_RUNNINGS, this);

            createTask.execute(running);
        } catch (FormException e) {
            runningFragment.onError(e.getMessage());
        }
    }

    private Running getRunningToCreate(RunningForm runningForm, Project project) throws FormException {
        Running runningFromForm = runningForm.getEditable();
        Running runningToCreate = new Running();

        runningToCreate.setTeacher(Cache.load(
                runningFragment.getContext(),
                SessionHelper.getExtraId(runningFragment.getContext(), Extra.TEACHER_ID),
                Teacher.class));
        runningToCreate.setProject(project);

        Binder.bind(runningToCreate, runningFromForm);

        return runningToCreate;
    }

    public void updateRunning(Running runningToUpdate, RunningForm runningForm) {
        taskType = TASKTYPE_UPDATE;

        Running runningFromForm = runningForm.getEditable();

        Binder.bind(runningToUpdate, runningFromForm);
        running = runningToUpdate;
        running.setTeacher(Cache.load(
                runningFragment.getContext(),
                SessionHelper.getExtraId(runningFragment.getContext(), Extra.TEACHER_ID),
                Teacher.class));

        updateTask = new UpdateTask<>(Running.class, WSInformation.URL_RUNNINGS, this);
        updateTask.execute(running);
    }

    public void deleteRunning(Running running) {
        taskType = TASKTYPE_DELETE;

        deleteTask = new DeleteTask<>(Running.class, WSInformation.URL_RUNNINGS, this);
        deleteTask.execute(running.getId());
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
        if (!isWSError(createTask.getWSResponseCode())) {
            try {
                long id = createTask.getResults().get(0);

                if (id > 0) {
                    running.setId(id);
                    Cache.keep(runningFragment.getContext(), running);
                    runningFragment.onCreateSucceeded();
                }
            } catch (IndexOutOfBoundsException e) {
                runningFragment.onError("Erreur de chargement des données");
            }
        }
    }

    private boolean isWSError(int code) {
        EnumWSCode wsCode = EnumWSCode.find(code);
        if (wsCode.isError()) {
            EnumWSCodeRes wsCodeRes = EnumWSCodeRes.find(wsCode);
            runningFragment.onError(runningFragment.getContext().getResources().getString(wsCodeRes.getStringRes()));
            return true;
        }

        return false;
    }

    private void onUpdateTaskSucceeded() {
        if (!isWSError(updateTask.getWSReponseCode())) {
            Cache.keep(runningFragment.getContext(), running);
            runningFragment.onUpdateSucceeded();
        }
    }

    private void onDeleteTaskSucceeded() {
        if (!isWSError(deleteTask.getWSReponseCode())) {
            runningFragment.onDeleteSucceeded();
        }
    }

    @Override
    public void onFail(int httpResponseCode) {
        runningFragment.onError("Echec de la connexion au serveur");
    }
}