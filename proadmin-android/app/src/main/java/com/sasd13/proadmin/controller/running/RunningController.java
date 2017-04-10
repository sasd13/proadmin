package com.sasd13.proadmin.controller.running;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.RunningScope;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.NewRunningBuilder;
import com.sasd13.proadmin.view.IBrowsable;
import com.sasd13.proadmin.view.IProjectController;
import com.sasd13.proadmin.view.IRunningController;
import com.sasd13.proadmin.view.fragment.running.RunningDetailsFragment;
import com.sasd13.proadmin.view.fragment.running.RunningNewFragment;

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
    public Object getScope() {
        return scope;
    }

    @Override
    public void actionNewRunning(Project project) {
        scope.setRunning(new NewRunningBuilder(project, SessionHelper.getExtraIdTeacherNumber(mainActivity)).build());
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