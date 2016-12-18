package com.sasd13.proadmin.controller.project;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.fragment.IProjectController;
import com.sasd13.proadmin.fragment.IRunningController;
import com.sasd13.proadmin.fragment.project.ProjectDetailsFragment;
import com.sasd13.proadmin.fragment.project.ProjectsFragment;
import com.sasd13.proadmin.fragment.running.RunningDetailsFragment;
import com.sasd13.proadmin.fragment.running.RunningNewFragment;
import com.sasd13.proadmin.service.ws.ProjectService;
import com.sasd13.proadmin.service.ws.RunningService;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningBuilder;
import com.sasd13.proadmin.util.wrapper.ProjectWrapper;
import com.sasd13.proadmin.util.wrapper.ProjectsWrapper;
import com.sasd13.proadmin.util.wrapper.RunningWrapper;

import java.util.List;

public class ProjectController extends Controller implements IProjectController, IRunningController {

    private ProjectService projectService;
    private RunningService runningService;
    private ProjectsWrapper projectsWrapper;
    private ProjectWrapper projectWrapper;

    public ProjectController(MainActivity mainActivity) {
        super(mainActivity);

        projectService = new ProjectService(new ProjectServiceCaller(this, mainActivity));
        runningService = new RunningService(new RunningServiceCaller(this, mainActivity));
    }

    @Override
    public void entry() {
        mainActivity.clearHistory();
        listProjects();
    }

    @Override
    public void listProjects() {
        projectsWrapper = new ProjectsWrapper();

        startProxyFragment();
        projectService.readAll();
    }

    void onReadProjects(List<Project> projects) {
        if (isProxyFragmentNotDetached()) {
            projectsWrapper.setProjects(projects);
            startFragment(ProjectsFragment.newInstance(projectsWrapper));
        } else if (isLoadingNext()) {
            projectsWrapper.setNextProjects(projects);
        }
    }

    @Override
    public void showProject(Project project) {
        projectWrapper = new ProjectWrapper(project);

        startProxyFragment();
        runningService.readByTeacherAndProject(SessionHelper.getExtraIdTeacherNumber(mainActivity), project.getCode());
    }

    void onReadRunnings(List<Running> runnings) {
        if (isProxyFragmentNotDetached()) {
            projectWrapper.setRunnings(runnings);
            startFragment(ProjectDetailsFragment.newInstance(projectWrapper));
        } else if (isLoadingNext()) {
            projectWrapper.setNextRunnings(runnings);
        }
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
        runningService.create(running);
    }

    @Override
    public void showRunning(Running running) {
        RunningWrapper runningWrapper = new RunningWrapper(running);
        runningWrapper.setProject(running.getProject());

        startFragment(RunningDetailsFragment.newInstance(runningWrapper));
    }

    @Override
    public void deleteRunning(Running running) {
        runningService.delete(running);
    }
}