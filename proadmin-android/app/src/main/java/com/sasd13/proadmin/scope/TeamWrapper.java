package com.sasd13.proadmin.scope;

import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class TeamWrapper extends Observable {

    private Team team;
    private List<StudentTeam> studentTeams;

    public TeamWrapper(Team team) {
        this.team = team;
        studentTeams = new ArrayList<>();
    }

    public Team getTeam() {
        return team;
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
