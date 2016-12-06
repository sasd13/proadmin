package com.sasd13.proadmin.controller.project;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.ProjectDependencyWrapper;
import com.sasd13.proadmin.view.IProjectController;
import com.sasd13.proadmin.view.IRunningController;
import com.sasd13.proadmin.view.project.ProjectDetailsFragment;
import com.sasd13.proadmin.view.project.ProjectsFragment;
import com.sasd13.proadmin.view.running.RunningDetailsFragment;
import com.sasd13.proadmin.view.running.RunningNewFragment;
import com.sasd13.proadmin.ws.service.ProjectService;
import com.sasd13.proadmin.ws.service.RunningService;

import java.util.List;

public class ProjectController extends Controller implements IProjectController, IRunningController {

    private ProjectService projectService;
    private RunningService runningService;
    private Project project;

    public ProjectController(MainActivity mainActivity) {
        super(mainActivity);

        projectService = new ProjectService(new ProjectServiceCaller(this, mainActivity));
        runningService = new RunningService(new RunningServiceCaller(this, mainActivity));
    }

    @Override
    public void entry() {
        listProjects();
    }

    @Override
    public void listProjects() {
        startProxyFragment();
        projectService.readAll();
    }

    void onReadProjects(List<Project> projects) {
        if (isProxyFragmentNotDetached()) {
            startFragment(ProjectsFragment.newInstance(this, projects));
        }
    }

    @Override
    public void showProject(Project project) {
        this.project = project;

        startProxyFragment();
        runningService.readByTeacherAndProject(SessionHelper.getExtraIdTeacherNumber(mainActivity), project.getCode());
    }

    void onReadRunnings(List<Running> runnings) {
        if (isProxyFragmentNotDetached()) {
            startFragment(ProjectDetailsFragment.newInstance(this, project, new ProjectDependencyWrapper(runnings)));
        }
    }

    @Override
    public void newRunning(Project project) {
        startFragment(RunningNewFragment.newInstance(this, project));
    }

    @Override
    public void createRunning(Running running) {
        runningService.create(running);
    }

    @Override
    public void showRunning(Running running) {
        startFragment(RunningDetailsFragment.newInstance(this, running));
    }

    @Override
    public void deleteRunning(Running running) {
        runningService.delete(running);
    }
}