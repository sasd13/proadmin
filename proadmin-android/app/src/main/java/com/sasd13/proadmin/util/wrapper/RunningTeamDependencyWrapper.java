package com.sasd13.proadmin.util.wrapper;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class RunningTeamDependencyWrapper extends Observable {

    private List<Running> runnings;
    private List<Team> teams;
    private List<AcademicLevel> academicLevels;
    private List<Report> reports;

    public RunningTeamDependencyWrapper() {
        runnings = new ArrayList<>();
        teams = new ArrayList<>();
        academicLevels = new ArrayList<>();
        reports = new ArrayList<>();
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
