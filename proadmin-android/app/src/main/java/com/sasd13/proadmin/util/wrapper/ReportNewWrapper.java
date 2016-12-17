package com.sasd13.proadmin.util.wrapper;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.RunningTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class ReportNewWrapper extends Observable {

    private List<RunningTeam> runningTeams;
    private List<StudentTeam> studentTeams;

    public ReportNewWrapper() {
        runningTeams = new ArrayList<>();
        studentTeams = new ArrayList<>();
    }

    public List<RunningTeam> getRunningTeams() {
        return runningTeams;
    }

    public void setRunningTeams(List<RunningTeam> runningTeams) {
        this.runningTeams = runningTeams;

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
