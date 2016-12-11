package com.sasd13.proadmin.util.wrapper;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class ReportWrapper extends Observable {

    private Report report;
    private List<StudentTeam> studentTeams;

    public ReportWrapper() {
        studentTeams = new ArrayList<>();
    }

    public ReportWrapper(Report report) {
        this();

        this.report = report;
    }

    public Report getReport() {
        return report;
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
