package com.sasd13.proadmin.ws;

import com.sasd13.proadmin.bean.project.Project;

import java.util.List;

public class ProjectWebService implements WebService<Project> {

    @Override
    public Project get(int id) {
        return null;
    }

    @Override
    public List<Project> get() {
        return null;
    }

    @Override
    public boolean post(Project project) {
        return false;
    }

    @Override
    public void put(Project project) {

    }

    @Override
    public void put(List<Project> list) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete() {

    }
}
