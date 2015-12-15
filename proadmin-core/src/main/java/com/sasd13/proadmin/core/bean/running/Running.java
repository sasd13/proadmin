package com.sasd13.proadmin.core.bean.running;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;

public class Running {

    private long id;
    private int year;
    private Teacher teacher;
    private Project project;
    private List<Team> teams;

    public Running() {
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        this.teams = new ArrayList<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void removeTeam(Team team) {
        this.teams.remove(team);
    }

    public Team[] getTeams() {
        return this.teams.toArray(new Team[this.teams.size()]);
    }
}
