package com.sasd13.proadmin.wsclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.wsclient.RestWebServiceClient;
import com.sasd13.proadmin.core.bean.project.Project;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ProjectRestWebServiceClient implements RestWebServiceClient<Project> {

    private static final String URL = "http://192.168.1.9:8080/proadmin-ws/projects";

    private Gson gson = new GsonBuilder().create();

    @Override
    public Project get(long id) {
        Project project = null;

        AsyncHttpGet asyncHttpGet = new AsyncHttpGet();
        String urlParams = "?id=" + id;

        try {
            asyncHttpGet.execute(new URL(URL + urlParams));
            String jsonData = asyncHttpGet.get(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);

            project = this.gson.fromJson(jsonData, Project.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return project;
    }

    @Override
    public Project[] getAll() {
        Project[] projects = new Project[0];

        AsyncHttpGet asyncHttpGet = new AsyncHttpGet();

        try {
            asyncHttpGet.execute(new URL(URL));
            String jsonData = asyncHttpGet.get(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);

            projects = this.gson.fromJson(jsonData, Project[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projects;
    }

    @Override
    public long post(Project project) {
        return 0;
    }

    @Override
    public void put(Project project) {}

    @Override
    public void putAll(Project[] projects) {}

    @Override
    public void delete(long l) {}

    @Override
    public void deleteAll() {}
}
