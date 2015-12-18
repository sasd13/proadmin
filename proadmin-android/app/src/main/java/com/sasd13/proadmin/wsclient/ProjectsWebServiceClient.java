package com.sasd13.proadmin.wsclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sasd13.javaex.wsclient.HttpRequest;
import com.sasd13.javaex.wsclient.RestWebServiceClient;
import com.sasd13.proadmin.core.bean.project.Project;

import java.net.MalformedURLException;
import java.net.URL;

public class ProjectsWebServiceClient implements RestWebServiceClient<Project> {

    private static final String URL = "http://192.168.1.9:8080/proadmin-ws/projects";

    private HttpRequest httpRequest;
    private Gson gson;

    public ProjectsWebServiceClient() {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public Project get(long id) {
        Project project = null;

        String urlParams = "?id=" + id;

        try {
            this.httpRequest = new HttpRequest(new URL(URL + urlParams));
            this.httpRequest.hasReponseData(true);
            this.httpRequest.execute();

            String jsonData = httpRequest.getResponseData();

            project = this.gson.fromJson(jsonData, Project.class);
        } catch (MalformedURLException | NullPointerException e) {
            e.printStackTrace();
        }

        return project;
    }

    @Override
    public Project[] getAll() {
        Project[] projects = null;

        try {
            this.httpRequest = new HttpRequest(new URL(URL));
            this.httpRequest.hasReponseData(true);
            this.httpRequest.execute();

            String jsonData = this.httpRequest.getResponseData();

            projects = this.gson.fromJson(jsonData, Project[].class);
        } catch (MalformedURLException | NullPointerException e) {
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
