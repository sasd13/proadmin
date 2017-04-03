package com.sasd13.proadmin.controller.project;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.IProjectController;
import com.sasd13.proadmin.controller.IRunningController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.view.fragment.project.ProjectDetailsFragment;
import com.sasd13.proadmin.view.fragment.project.ProjectsFragment;
import com.sasd13.proadmin.view.fragment.running.RunningDetailsFragment;
import com.sasd13.proadmin.view.fragment.running.RunningNewFragment;
import com.sasd13.proadmin.service.IProjectService;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.util.wrapper.ProjectScope;
import com.sasd13.proadmin.util.wrapper.RunningWrapper;

import java.util.List;

public class ProjectController extends MainController implements IProjectController, IRunningController {

    private IProjectService projectService;
    private IRunningService runningService;
    private ProjectReadStrategy projectReadStrategy;
    private RunningReadStrategy runningReadStrategy;
    private RunningCreateStrategy runningCreateStrategy;
    private RunningDeleteStrategy runningDeleteStrategy;
    private ProjectScope projectScope;
    private ProjectWrapper projectWrapper;

    public ProjectController(MainActivity mainActivity, IProjectService projectService, IRunningService runningService) {
        super(mainActivity);

        this.projectService = projectService;
        this.runningService = runningService;
    }

    @Override
    public void entry() {
        mainActivity.clearHistory();
        listProjects();
    }

    @Override
    public void listProjects() {
        projectScope = new ProjectScope();

        startFragment(ProjectsFragment.newInstance(projectScope));
        readProjects();
    }

    private void readProjects() {
        if (projectReadStrategy == null) {
            projectReadStrategy = new ProjectReadStrategy(this, projectService);
        }

        new Requestor(projectReadStrategy).execute();
    }

    void onReadProjects(List<Project> projects) {
        if (isProxyFragmentNotDetached()) {
            projectScope.setProjects(projects);
        }
    }

    @Override
    public void showProject(Project project) {
        projectWrapper = new ProjectWrapper(project);

        startFragment(ProjectDetailsFragment.newInstance(projectWrapper));
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
        projectWrapper.setRunnings(runnings);
    }

    @Override
    public void newRunning(Project project) {
        Running running = new DefaultRunningBuilder(project, SessionHelper.getExtraIdTeacherNumber(mainActivity)).build();
        RunningWrapper runningWrapper = new RunningWrapper(running);
        runningWrapper.setProject(project);

        startFragment(RunningNewFragment.newInstance(runningWrapper));
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
        RunningWrapper runningWrapper = new RunningWrapper(running);
        runningWrapper.setProject(running.getProject());

        startFragment(RunningDetailsFragment.newInstance(runningWrapper));
    }

    @Override
    public void deleteRunnings(Running[] runnings) {
        if (runningDeleteStrategy == null) {
            runningDeleteStrategy = new RunningDeleteStrategy(this, runningService);
        }

        new Requestor(runningDeleteStrategy).execute(runnings);
    }
}