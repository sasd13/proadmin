package com.sasd13.proadmin.android.controller.running;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.RunningScope;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IRunningService;
import com.sasd13.proadmin.android.util.builder.NewRunningBuilder;
import com.sasd13.proadmin.android.view.IBrowsable;
import com.sasd13.proadmin.android.view.IProjectController;
import com.sasd13.proadmin.android.view.IRunningController;
import com.sasd13.proadmin.android.view.fragment.running.RunningDetailsFragment;
import com.sasd13.proadmin.android.view.fragment.running.RunningNewFragment;

import java.util.Arrays;

public class RunningController extends MainController implements IRunningController {

    private RunningScope scope;
    private IRunningService runningService;
    private RunningCreateTask runningCreateTask;
    private RunningDeleteTask runningDeleteTask;

    public RunningController(MainActivity mainActivity, IRunningService runningService) {
        super(mainActivity);

        scope = new RunningScope();
        this.runningService = runningService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void actionNewRunning(Project project, Teacher teacher) {
        scope.setRunning(new NewRunningBuilder(project, teacher).build());
        startFragment(RunningNewFragment.newInstance());
    }

    @Override
    public void actionCreateRunning(Running running) {
        if (runningCreateTask == null) {
            runningCreateTask = new RunningCreateTask(this, runningService);
        }

        new Requestor(runningCreateTask).execute(running);
    }

    void onCreateRunning() {
        display(R.string.message_saved);
        ((IBrowsable) mainActivity.lookup(IProjectController.class)).browse();
    }

    @Override
    public void actionShowRunning(Running running) {
        scope.setRunning(running);
        startFragment(RunningDetailsFragment.newInstance());
    }

    @Override
    public void actionRemoveRunning(Running running) {
        if (runningDeleteTask == null) {
            runningDeleteTask = new RunningDeleteTask(this, runningService);
        }

        new Requestor(runningDeleteTask).execute(Arrays.asList(new Running[]{running}));
    }

    void onDeleteRunnings() {
        display(R.string.message_deleted);
        ((IBrowsable) mainActivity.lookup(IProjectController.class)).browse();
    }
}