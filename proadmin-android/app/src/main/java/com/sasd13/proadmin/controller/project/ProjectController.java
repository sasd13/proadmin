package com.sasd13.proadmin.controller.project;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.ProjectScope;
import com.sasd13.proadmin.service.IProjectService;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.view.fragment.project.IProjectController;
import com.sasd13.proadmin.view.fragment.project.ProjectDetailsFragment;
import com.sasd13.proadmin.view.fragment.project.ProjectsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectController extends MainController implements IProjectController {

    private ProjectScope scope;
    private IProjectService projectService;
    private IRunningService runningService;
    private ProjectReadTask projectReadTask;
    private RunningReadTask runningReadTask;

    public ProjectController(MainActivity mainActivity, IProjectService projectService, IRunningService runningService) {
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
        scope.setProjects(new ArrayList<Project>());
        startFragment(ProjectsFragment.newInstance());
        readProjects();
    }

    private void readProjects() {
        if (projectReadTask == null) {
            projectReadTask = new ProjectReadTask(this, projectService);
        }

        new Requestor(projectReadTask).execute();
    }

    void onReadProjects(List<Project> projects) {
        int index;

        for (Project project : projects) {
            if ((index = scope.getProjects().indexOf(project)) >= 0) {
                projects.remove(project);
                scope.getProjects().set(index, project);
            } else {
                scope.getProjects().add(project);
            }
        }

        scope.setProjectsToAdd(projects);
        scope.setProjectsToAdd(Collections.<Project>emptyList());
    }

    @Override
    public void actionShowProject(Project project) {
        scope.setProject(project);
        scope.setRunnings(new ArrayList<Running>());
        startFragment(ProjectDetailsFragment.newInstance());
        readRunnings(project);
    }

    private void readRunnings(Project project) {
        if (runningReadTask == null) {
            runningReadTask = new RunningReadTask(this, runningService);
        }

        runningReadTask.clearParameters();
        runningReadTask.putParameter(EnumParameter.PROJECT.getName(), new String[]{project.getCode()});
        runningReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(runningReadTask).execute();
    }

    void onReadRunnings(List<Running> runnings) {
        scope.setRunnings(runnings);
    }
}