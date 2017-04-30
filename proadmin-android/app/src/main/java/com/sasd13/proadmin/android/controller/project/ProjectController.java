package com.sasd13.proadmin.android.controller.project;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.ProjectScope;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.v1.IProjectService;
import com.sasd13.proadmin.android.service.v1.IRunningService;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.view.IProjectController;
import com.sasd13.proadmin.android.view.fragment.project.ProjectDetailsFragment;
import com.sasd13.proadmin.android.view.fragment.project.ProjectsFragment;
import com.sasd13.proadmin.util.EnumParameter;

import java.util.ArrayList;
import java.util.Iterator;
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
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        startFragment(ProjectsFragment.newInstance());
        actionLoadProjects();
    }

    @Override
    public void actionLoadProjects() {
        readProjects();
    }

    private void readProjects() {
        scope.setLoading(true);

        if (projectReadTask == null) {
            projectReadTask = new ProjectReadTask(this, projectService);
        }

        new Requestor(projectReadTask).execute();
    }

    void onReadProjects(List<Project> projects) {
        int index;
        Project project;

        for (Iterator<Project> it = projects.iterator(); it.hasNext(); ) {
            project = it.next();

            if ((index = scope.getProjects().indexOf(project)) >= 0) {
                it.remove();
                scope.getProjects().set(index, project);
            } else {
                scope.getProjects().add(project);
            }
        }

        scope.setProjectsToAdd(projects);

        if (!projects.isEmpty()) {
            scope.clearProjectsToAdd();
        }

        scope.setLoading(false);
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
        runningReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIntermediary(mainActivity)});
        new Requestor(runningReadTask).execute();
    }

    void onReadRunnings(List<Running> runnings) {
        scope.setRunnings(runnings);
    }
}