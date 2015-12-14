package com.sasd13.proadmin.ws;

import com.sasd13.wsprovider.proadmin.bean.AcademicLevel;
import com.sasd13.wsprovider.proadmin.bean.project.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectWSConsumer implements WSConsumer<Project> {

    @Override
    public Project get(long id) {
        return null;
    }

    @Override
    public List<Project> get() {
        List<Project> list = new ArrayList<>();

        //TEST
        Project project;

        project = new Project();
        project.setId(1);
        project.setAcademicLevel(AcademicLevel.YEAR1);
        project.setCode("L1A");
        project.setTitle("Projet A");
        project.setDescription("Description du projet A");

        list.add(project);

        project = new Project();
        project.setId(2);
        project.setAcademicLevel(AcademicLevel.YEAR2);
        project.setCode("L2B");
        project.setTitle("Projet B");
        project.setDescription("Description du projet B");

        list.add(project);

        project = new Project();
        project.setId(3);
        project.setAcademicLevel(AcademicLevel.YEAR2);
        project.setCode("L2C");
        project.setTitle("Projet C");
        project.setDescription("Description du projet C");

        list.add(project);
        //FIN DE TEST

        return list;
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
    public void delete(long id) {

    }

    @Override
    public void delete() {

    }
}
