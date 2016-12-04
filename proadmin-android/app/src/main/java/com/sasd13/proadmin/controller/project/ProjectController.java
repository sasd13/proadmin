package com.sasd13.proadmin.controller.project;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.view.project.IProjectController;
import com.sasd13.proadmin.view.project.ProjectDetailsFragment;
import com.sasd13.proadmin.view.project.ProjectsFragment;
import com.sasd13.proadmin.view.running.IRunningController;
import com.sasd13.proadmin.view.running.RunningDetailsFragment;
import com.sasd13.proadmin.view.running.RunningNewFragment;
import com.sasd13.proadmin.ws.service.ProjectService;
import com.sasd13.proadmin.ws.service.RunningService;

import java.util.List;

public class ProjectController extends Controller implements IProjectController, IRunningController {

    private ProjectService projectService;
    private RunningService runningService;

    private ProjectsFragment projectsFragment;
    private ProjectDetailsFragment projectDetailsFragment;

    public ProjectController(MainActivity mainActivity) {
        super(mainActivity);

        projectService = new ProjectService(new ProjectServiceCaller(this, mainActivity));
        runningService = new RunningService(new RunningServiceCaller(this, mainActivity));
    }

    @Override
    public void listProjects() {
        projectsFragment = ProjectsFragment.newInstance(this);

        startFragment(projectsFragment);
        projectService.readAll();
    }

    void onReadProjects(List<Project> projects) {
        projectsFragment.setProjects(projects);
    }

    @Override
    public void showProject(Project project) {
        projectDetailsFragment = ProjectDetailsFragment.newInstance(this, project);

        startFragment(projectDetailsFragment);
        runningService.read(SessionHelper.getExtraIdTeacherNumber(mainActivity), project.getCode());
    }

    void onReadRunnings(List<Running> runnings) {
        projectDetailsFragment.setRunnings(runnings);
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