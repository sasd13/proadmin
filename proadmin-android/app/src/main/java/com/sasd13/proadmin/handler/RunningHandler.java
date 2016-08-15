package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.CreateTask;
import com.sasd13.androidex.net.ws.rest.task.DeleteTask;
import com.sasd13.androidex.net.ws.rest.task.ReadTask;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.builder.runing.DefaultRunningBuilder;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.form.FormException;
import com.sasd13.proadmin.form.RunningForm;
import com.sasd13.proadmin.fragment.running.RunningFragment;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.ws.WSInformation;

public class RunningHandler implements IWSPromise {

    private static final int TYPE_CREATE = 0;
    private static final int TYPE_READ = 1;
    private static final int TYPE_UPDATE = 2;
    private static final int TYPE_DELETE = 3;

    private RunningFragment runningFragment;
    private DefaultRunningBuilder defaultRunningBuilder;
    private CreateTask<Running> createTask;
    private ReadTask<Running> readTask;
    private DeleteTask<Running> deleteTask;
    private Running runningToCreate;
    private int taskType;

    public RunningHandler(RunningFragment runningFragment) {
        this.runningFragment = runningFragment;
        defaultRunningBuilder = new DefaultRunningBuilder();
    }

    public Running getDefaultValueOfRunning() {
        return defaultRunningBuilder.build();
    }

    public void createRunning(RunningForm runningForm, Project project) {
        try {
            runningToCreate = getRunningToCreate(runningForm, project);
            createTask = new CreateTask<>(WSInformation.URL_RUNNINGS, this);

            createTask.execute(runningToCreate);
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

    public void readRunning(long id) {
        readTask = new ReadTask<>(WSInformation.URL_RUNNINGS, this);
        readTask.execute(id);
    }

    public void updateRunning(Running running, RunningForm runningForm) {
        try {
            Running runningFromForm = runningForm.getEditable();

            Binder.bind(running, runningFromForm);
            Cache.keep(runningFragment.getContext(), running);
            runningFragment.onUpdateSucceeded();
        } catch (FormException e) {
            runningFragment.onError(e.getMessage());
        }
    }

    public void deleteRunning(Running running) {
        deleteTask = new DeleteTask<>(WSInformation.URL_RUNNINGS, this);

    }

    @Override
    public void onLoad() {
        runningFragment.onLoad();
    }

    @Override
    public void onSuccess() {
        switch (taskType) {
            case TYPE_CREATE:
                onCreateTaskSucceeded();
                break;
            case TYPE_READ:
                onReadTaskSucceeded();
                break;
            case TYPE_UPDATE:
                onUpdateTaskSucceeded();
                break;
            case TYPE_DELETE:
                onDeleteTaskSucceeded();
                break;
        }
    }

    private void onCreateTaskSucceeded() {
        try {
            long id = createTask.getResults().get(0);

            if (id > 0) {
                runningToCreate.setId(id);
                Cache.keep(runningFragment.getContext(), runningToCreate);
                runningFragment.onCreateSucceeded(runningToCreate);
            }
        } catch (IndexOutOfBoundsException e) {
            runningFragment.onError("Erreur de chargement des données");
        }
    }

    private void onReadTaskSucceeded() {
        try {
            Cache.keep(runningFragment.getContext(), readTask.getResults().get(0));
            runningFragment.onReadSucceeded(readTask.getResults().get(0));
        } catch (IndexOutOfBoundsException e) {
            runningFragment.onError("Erreur de chargement des données");
        }
    }

    private void onDeleteTaskSucceeded() {
        runningFragment.onDeleteSucceeded();
    }

    @Override
    public void onFail(int httpResponseCode) {
        runningFragment.onError("Echec de la connexion au serveur");
    }
}