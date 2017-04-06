package com.sasd13.proadmin.scope;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class TeamScope extends Observable {

    private List<Team> teams;
    private Team team;
    private List<StudentTeam> studentTeams;
    private Student student;

    public TeamScope() {
        teams = new ArrayList<>();
        studentTeams = new ArrayList<>();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;

        setChanged();
        notifyObservers();
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;

        setChanged();
        notifyObservers();
    }

    public List<StudentTeam> getStudentTeams() {
        return studentTeams;
    }

    public void setStudentTeams(List<StudentTeam> studentTeams) {
        this.studentTeams = studentTeams;

        setChanged();
        notifyObservers();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;

        setChanged();
        notifyObservers();
    }
}
