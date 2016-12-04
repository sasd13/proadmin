package com.sasd13.proadmin.util.wrapper;

import com.sasd13.proadmin.bean.member.StudentTeam;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class ReportDependencyWrapper {

    private List<StudentTeam> studentTeams;

    public ReportDependencyWrapper(List<StudentTeam> studentTeams) {
        this.studentTeams = studentTeams;
    }

    public List<StudentTeam> getStudentTeams() {
        return studentTeams;
    }
}
