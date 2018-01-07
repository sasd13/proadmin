package com.sasd13.proadmin.android.scope;

import com.sasd13.proadmin.android.model.AcademicLevel;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class RunningTeamScope extends Scope {

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

    public void clearRunningTeamsToAdd() {
        runningTeamsToAdd = Collections.emptyList();
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
