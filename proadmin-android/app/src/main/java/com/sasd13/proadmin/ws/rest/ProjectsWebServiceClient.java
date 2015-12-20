package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.net.HttpRequest;
import com.sasd13.javaex.net.parser.DataParser;
import com.sasd13.javaex.net.ws.rest.WebServiceClient;
import com.sasd13.proadmin.core.bean.project.Project;

import java.net.MalformedURLException;
import java.net.URL;

public class ProjectsWebServiceClient implements WebServiceClient<Project> {

    private static final String URL = "http://192.168.1.9:8080/proadmin-ws/projects";

    private HttpRequest httpRequest;

    @Override
    public Project get(long id) {
        Project project = null;

        String urlParams = "?id=" + id;

        try {
            httpRequest = new HttpRequest(new URL(URL + urlParams));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String respData = httpRequest.getResponseData();
            String mimeType = httpRequest.getResponseContentType();

            project = (Project) DataParser.decode(mimeType, respData, Project.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return project;
    }

    @Override
    public Project[] getAll() {
        Project[] projects = null;

        try {
            httpRequest = new HttpRequest(new URL(URL));
            httpRequest.hasReponseData(true);
            httpRequest.execute();

            String respData = httpRequest.getResponseData();
            String mimeType = httpRequest.getResponseContentType();

            projects = (Project[]) DataParser.decode(mimeType, respData, Project.class);
        } catch (MalformedURLException e) {
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
