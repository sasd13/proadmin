package com.sasd13.proadmin.android.controller.project;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.ProjectScope;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IProjectService;
import com.sasd13.proadmin.android.service.IRunningService;
import com.sasd13.proadmin.android.service.ITeacherService;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.view.IProjectController;
import com.sasd13.proadmin.android.view.fragment.project.ProjectDetailsFragment;
import com.sasd13.proadmin.android.view.fragment.project.ProjectsFragment;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.util.EnumRestriction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProjectController extends MainController implements IProjectController {

    private ProjectScope scope;
    private IProjectService projectService;
    private IRunningService runningService;
    private ITeacherService teacherService;
    private ProjectReadTask projectReadTask;
    private RunningReadTask runningReadTask;
    private TeacherReadTask teacherReadTask;
    private Map<String, String[]> runningCriterias, teacherCriterias;

    public ProjectController(MainActivity mainActivity, IProjectService projectService, IRunningService runningService, ITeacherService teacherService) {
        super(mainActivity);

        scope = new ProjectScope();
        this.projectService = projectService;
        this.runningService = runningService;
        this.teacherService = teacherService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        getActivity().clearHistory();
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

        if (scope.getTeacher() == null) {
            readTeacher();
        }
    }

    private void readRunnings(Project project) {
        if (runningReadTask == null) {
            runningReadTask = new RunningReadTask(this, runningService);
            runningCriterias = new HashMap<>();
        } else {
            runningCriterias.clear();
        }

        Map<String, Object> allCriterias = new HashMap<>();

        runningCriterias.put(EnumCriteria.PROJECT.getCode(), new String[]{project.getCode()});
        runningCriterias.put(EnumCriteria.TEACHER.getCode(), new String[]{SessionHelper.getExtraIntermediary(getActivity())});
        allCriterias.put(EnumRestriction.WHERE.getCode(), runningCriterias);

        new Requestor(runningReadTask).execute(allCriterias);
    }

    void onReadRunnings(List<Running> runnings) {
        scope.setRunnings(runnings);
    }

    private void readTeacher() {
        if (teacherReadTask == null) {
            teacherReadTask = new TeacherReadTask(this, teacherService);
            teacherCriterias = new HashMap<>();
        } else {
            teacherCriterias.clear();
        }

        Map<String, Object> allCriterias = new HashMap<>();

        teacherCriterias.put(EnumCriteria.INTERMEDIARY.getCode(), new String[]{SessionHelper.getExtraIntermediary(getActivity())});
        allCriterias.put(EnumRestriction.WHERE.getCode(), teacherCriterias);

        new Requestor(teacherReadTask).execute(allCriterias);
    }

    void onReadTeacher(Teacher teacher) {
        scope.setTeacher(teacher);
    }
}