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

        HttpGet httpGet = new HttpGet();
        String urlParams = "?id=" + id;

        try {
            httpGet.execute(new URL(URL + urlParams));
            String jsonData = httpGet.get(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);

            project = this.gson.fromJson(jsonData, Project.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return project;
    }

    @Override
    public Project[] getAll() {
        Project[] projects = new Project[0];

        HttpGet httpGet = new HttpGet();

        try {
            httpGet.execute(new URL(URL));
            String jsonData = httpGet.get(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);

            projects = this.gson.fromJson(jsonData, Project[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projects;
    }

    @Override
    public boolean post(Project project) {
        return false;
    }

    @Override
    public void put(Project project) {

    }

    @Override
    public void putAll(Project[] list) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void deleteAll() {

    }
}
