package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class RunningTeamScope extends Observable {

    private boolean loading;
    private List<RunningTeam> runningTeams, runningTeamsToAdd;
    private RunningTeam runningTeam;
    private List<Running> runnings;
    private List<Team> teams;
    private List<AcademicLevel> academicLevels;
    private List<Report> reports;

    public RunningTeamScope() {
        runningTeams = new ArrayList<>();
        runningTeamsToAdd = Collections.emptyList();
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;

        setChanged();
        notifyObservers();
    }

    public List<RunningTeam> getRunningTeams() {
        return runningTeams;
    }

    public void setRunningTeams(List<RunningTeam> runningTeams) {
        this.runningTeams = runningTeams;

        setChanged();
        notifyObservers();
    }

    public List<RunningTeam> getRunningTeamsToAdd() {
        return runningTeamsToAdd;
    }

    public void setRunningTeamsToAdd(List<RunningTeam> runningTeamsToAdd) {
        this.runningTeamsToAdd = runningTeamsToAdd;

        setChanged();
        notifyObservers();
    }

    public RunningTeam getRunningTeam() {
        return runningTeam;
    }

    public void setRunningTeam(RunningTeam runningTeam) {
        this.runningTeam = runningTeam;

        setChanged();
        notifyObservers();
    }

    public List<Running> getRunnings() {
        return runnings;
    }

    public void setRunnings(List<Running> runnings) {
        this.runnings = runnings;

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

    public List<AcademicLevel> getAcademicLevels() {
        return academicLevels;
    }

    public void setAcademicLevels(List<AcademicLevel> academicLevels) {
        this.academicLevels = academicLevels;

        setChanged();
        notifyObservers();
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;

        setChanged();
        notifyObservers();
    }
}
