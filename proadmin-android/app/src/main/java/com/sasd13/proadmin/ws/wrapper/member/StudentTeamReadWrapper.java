package com.sasd13.proadmin.ws.wrapper.member;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class StudentTeamReadWrapper implements IReadWrapper<List<StudentTeam>> {

    private List<StudentTeam> studentTeams;

    public StudentTeamReadWrapper(List<StudentTeam> studentTeams) {
        this.studentTeams = studentTeams;
    }

    @Override
    public List<StudentTeam> getWrapped() {
        return studentTeams;
    }
}
