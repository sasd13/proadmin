package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class TeamScope extends Observable {

    private boolean loading;
    private List<Team> teams, teamsToAdd;
    private Team team;
    private List<StudentTeam> studentTeams;

    public TeamScope() {
        teams = new ArrayList<>();
        teamsToAdd = Collections.emptyList();
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;

        setChanged();
        notifyObservers();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;

        setChanged();
        notifyObservers();
    }

    public List<Team> getTeamsToAdd() {
        return teamsToAdd;
    }

    public void setTeamsToAdd(List<Team> teamsToAdd) {
        this.teamsToAdd = teamsToAdd;

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
}
