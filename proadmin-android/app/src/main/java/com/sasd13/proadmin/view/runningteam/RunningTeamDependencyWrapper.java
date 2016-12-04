package com.sasd13.proadmin.view.runningteam;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class RunningTeamDependencyWrapper {

    private List<Running> runnings;
    private List<Team> teams;
    private List<AcademicLevel> academicLevels;

    public RunningTeamDependencyWrapper(List<Running> runnings, List<Team> teams, List<AcademicLevel> academicLevels) {
        this.runnings = runnings;
        this.teams = teams;
        this.academicLevels = academicLevels;
    }

    public List<Running> getRunnings() {
        return runnings;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<AcademicLevel> getAcademicLevels() {
        return academicLevels;
    }
}
