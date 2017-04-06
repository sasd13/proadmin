package com.sasd13.proadmin.controller.running;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.IBrowsable;
import com.sasd13.proadmin.controller.IProjectController;
import com.sasd13.proadmin.controller.IRunningController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.controller.project.ProjectReadStrategy;
import com.sasd13.proadmin.controller.project.RunningCreateStrategy;
import com.sasd13.proadmin.controller.project.RunningDeleteStrategy;
import com.sasd13.proadmin.controller.project.RunningReadStrategy;
import com.sasd13.proadmin.scope.ProjectScope;
import com.sasd13.proadmin.service.IProjectService;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.view.fragment.project.ProjectDetailsFragment;
import com.sasd13.proadmin.view.fragment.project.ProjectsFragment;
import com.sasd13.proadmin.view.fragment.running.RunningDetailsFragment;
import com.sasd13.proadmin.view.fragment.running.RunningNewFragment;

import java.util.List;

public class RunningController extends MainController implements IProjectController, IRunningController, IBrowsable {

    private ProjectScope scope;
    private IProjectService projectService;
    private IRunningService runningService;
    private ProjectReadStrategy projectReadStrategy;
    private RunningReadStrategy runningReadStrategy;
    private RunningCreateStrategy runningCreateStrategy;
    private RunningDeleteStrategy runningDeleteStrategy;

    public RunningController(MainActivity mainActivity, IProjectService projectService, IRunningService runningService) {
        super(mainActivity);

        scope = new ProjectScope();
        this.projectService = projectService;
        this.runningService = runningService;
    }

    @Override
    public Object getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        listProjects();
    }

    @Override
    public void listProjects() {
        startFragment(ProjectsFragment.newInstance());
        readProjects();
    }

    private void readProjects() {
        if (projectReadStrategy == null) {
            projectReadStrategy = new ProjectReadStrategy(this, projectService);
        }

        new Requestor(projectReadStrategy).execute();
    }

    void onReadProjects(List<Project> projects) {
        scope.setProjects(projects);
    }

    @Override
    public void showProject(Project project) {
        scope.setProject(project);

        startFragment(ProjectDetailsFragment.newInstance());
        listRunnings(project);
    }

    @Override
    public void listRunnings(Project project) {
        if (runningReadStrategy == null) {
            runningReadStrategy = new RunningReadStrategy(this, runningService);
        }

        runningReadStrategy.clearParameters();
        runningReadStrategy.putParameter(EnumParameter.PROJECT.getName(), new String[]{project.getCode()});
        runningReadStrategy.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(runningReadStrategy).execute();
    }

    void onReadRunnings(List<Running> runnings) {
        scope.setRunnings(runnings);
    }

    @Override
    public void newRunning(Project project) {
        scope.setRunning(new DefaultRunningBuilder(project, SessionHelper.getExtraIdTeacherNumber(mainActivity)).build());

        startFragment(RunningNewFragment.newInstance());
    }

    @Override
    public void createRunning(Running running) {
        if (runningCreateStrategy == null) {
            runningCreateStrategy = new RunningCreateStrategy(this, runningService);
        }

        new Requestor(runningCreateStrategy).execute(running);
    }

    @Override
    public void showRunning(Running running) {
        scope.setRunning(running);

        startFragment(RunningDetailsFragment.newInstance());
    }

    @Override
    public void deleteRunnings(Running[] runnings) {
        if (runningDeleteStrategy == null) {
            runningDeleteStrategy = new RunningDeleteStrategy(this, runningService);
        }

        new Requestor(runningDeleteStrategy).execute(runnings);
    }
}