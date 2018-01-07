package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.android.model.StudentTeam;
import com.sasd13.proadmin.android.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class TeamScope extends Scope {

    private List<Team> teams, teamsToAdd;
    private Team team;
    private List<StudentTeam> studentTeams;

    public TeamScope() {
        teams = new ArrayList<>();
        teamsToAdd = Collections.emptyList();
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

    public void clearTeamsToAdd() {
        teamsToAdd = Collections.emptyList();
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
