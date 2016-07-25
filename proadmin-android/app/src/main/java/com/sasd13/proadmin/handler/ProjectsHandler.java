package com.sasd13.proadmin.handler;

import com.sasd13.androidex.net.ws.IWSPromise;
import com.sasd13.androidex.net.ws.rest.task.ReadTask;
import com.sasd13.proadmin.ProjectsActivity;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.cache.Cache;
import com.sasd13.proadmin.util.EnumParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 24/07/2016.
 */
public class ProjectsHandler implements IWSPromise {

    private ProjectsActivity projectsActivity;
    private ReadTask<Project> readTaskProjects;

    public ProjectsHandler(ProjectsActivity projectsActivity) {
        this.projectsActivity = projectsActivity;
    }

    public void readProjects(long teacherId) {

    }

    public List<Project> readProjectsFromCache(long teacherId) {
        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(EnumParameter.TEACHER.getName(), new String[]{ String.valueOf(teacherId)});

        return Cache.load(projectsActivity, parameters, Project.class);
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onSuccess() {
        Cache.keepAll(projectsActivity, readTaskProjects.getResults());
    }

    @Override
    public void onFail(int httpResponseCode) {

    }
}
