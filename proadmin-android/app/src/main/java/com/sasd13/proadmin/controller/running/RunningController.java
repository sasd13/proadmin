package com.sasd13.proadmin.controller.running;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.IRunningController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.RunningScope;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.view.fragment.running.RunningDetailsFragment;
import com.sasd13.proadmin.view.fragment.running.RunningNewFragment;

public class RunningController extends MainController implements IRunningController {

    private RunningScope scope;
    private IRunningService runningService;
    private RunningCreateStrategy runningCreateStrategy;
    private RunningDeleteStrategy runningDeleteStrategy;

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
        scope.setRunning(new DefaultRunningBuilder(project, SessionHelper.getExtraIdTeacherNumber(mainActivity)).build());
        startFragment(RunningNewFragment.newInstance());
    }

    @Override
    public void actionCreateRunning(Running running) {
        if (runningCreateStrategy == null) {
            runningCreateStrategy = new RunningCreateStrategy(this, runningService);
        }

        new Requestor(runningCreateStrategy).execute(running);
    }

    void onCreateRunning() {
        display(R.string.message_saved);
        //TODO entry();
    }

    @Override
    public void showRunning(Running running) {
        scope.setRunning(running);
        startFragment(RunningDetailsFragment.newInstance());
    }

    @Override
    public void actionRemoveRunning(Running running) {
        if (runningDeleteStrategy == null) {
            runningDeleteStrategy = new RunningDeleteStrategy(this, runningService);
        }

        new Requestor(runningDeleteStrategy).execute(new Running[]{running});
    }

    void onDeleteRunnings() {
        display(R.string.message_deleted);
        //TODO entry();
    }
}